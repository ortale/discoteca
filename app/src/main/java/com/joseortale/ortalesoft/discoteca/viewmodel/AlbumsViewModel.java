package com.joseortale.ortalesoft.discoteca.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.joseortale.ortalesoft.discoteca.data.api.AlbumsRepository;
import com.joseortale.ortalesoft.discoteca.model.Album;

import java.util.List;

public class AlbumsViewModel extends ViewModel {
    private MutableLiveData<List<Album>> mutableLiveData;
    private AlbumsRepository albumsRepository;

    public void init(Context context) {
        if (mutableLiveData != null){
            return;
        }

        albumsRepository = AlbumsRepository.getInstance(context);
        mutableLiveData = albumsRepository.getAlbums();
    }

    public LiveData<List<Album>> getAlbumsRepository() {
        return mutableLiveData;
    }
}
