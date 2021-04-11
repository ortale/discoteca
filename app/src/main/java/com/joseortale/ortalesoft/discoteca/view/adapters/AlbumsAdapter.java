package com.joseortale.ortalesoft.discoteca.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.joseortale.ortalesoft.discoteca.R;
import com.joseortale.ortalesoft.discoteca.model.Album;

import java.util.List;

public class AlbumsAdapter  extends RecyclerView.Adapter<AlbumsAdapter.ViewHolder> {
    private List<Album> data;
    private LayoutInflater inflater;

    public AlbumsAdapter(Context context, List<Album> data) {
        this.inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.album_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Album album = data.get(position);
        holder.tvTitle.setText(album.getTitle());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTitle;

        ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
