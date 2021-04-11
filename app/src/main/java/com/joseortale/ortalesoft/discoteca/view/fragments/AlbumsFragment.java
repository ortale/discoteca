package com.joseortale.ortalesoft.discoteca.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.joseortale.ortalesoft.discoteca.R;
import com.joseortale.ortalesoft.discoteca.model.Album;
import com.joseortale.ortalesoft.discoteca.view.adapters.AlbumsAdapter;
import com.joseortale.ortalesoft.discoteca.viewmodel.AlbumsViewModel;

import java.util.ArrayList;
import java.util.List;

public class AlbumsFragment extends Fragment {
    private Context context;

    private List<Album> albumArrayList = new ArrayList<>();
    private RecyclerView.Adapter albumsAdapter;
    private RecyclerView rvAlbums;
    private AlbumsViewModel albumsViewModel;

    public static AlbumsFragment newInstance() {
        AlbumsFragment fragment = new AlbumsFragment();

        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_albums, container, false);

        rvAlbums = view.findViewById(R.id.rvAlbums);

        albumsViewModel = ViewModelProviders.of(this).get(AlbumsViewModel.class);
        albumsViewModel.init(context);
        albumsViewModel.getAlbumsRepository().observe(this, albumArrayList -> {
            this.albumArrayList = albumArrayList;
            refreshRecyclerView();
        });

        refreshRecyclerView();

        return view;
    }

    private void refreshRecyclerView() {
        albumsAdapter = new AlbumsAdapter(context, albumArrayList);

        rvAlbums.setLayoutManager(new LinearLayoutManager(context));
        rvAlbums.setAdapter(albumsAdapter);
        rvAlbums.setItemAnimator(new DefaultItemAnimator());
        rvAlbums.setNestedScrollingEnabled(true);
        albumsAdapter.notifyDataSetChanged();
    }
}
