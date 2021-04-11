package com.joseortale.ortalesoft.discoteca.data.api;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.joseortale.ortalesoft.discoteca.data.database.AlbumDao;
import com.joseortale.ortalesoft.discoteca.data.database.AppDatabase;
import com.joseortale.ortalesoft.discoteca.model.Album;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumsRepository {
    private static AlbumsRepository instance;

    private final ApiInterface client;
    private final Context context;
    private AppDatabase database;
    private AlbumDao albumDao;
    private List<Album> albums;

    private AlbumsRepository(Context context) {
        this.context = context;

        client = RetrofitClient.getClient();
        database = Room.databaseBuilder(context, AppDatabase.class, "albums").allowMainThreadQueries().build();
        albumDao = database.albumDao();
    }

    public static AlbumsRepository getInstance(Context context) {
        if (instance == null) {
            instance = new AlbumsRepository(context);
        }

        return instance;
    }

    private boolean doesAlbumExists(Album album) {
        albums = albumDao.getAll();

        for (Album existingAlbum : albums) {
            if (existingAlbum.getId().equals(album.getId())) {
                return true;
            }
        }

        return false;
    }

    private void insertAlbums(List<Album> albums) {
        for (Album album : albums) {
            if (!doesAlbumExists(album)) {
                albumDao.insertAll(album);
            }

            else {
                albumDao.update(album);
            }
        }
    }

    public MutableLiveData<List<Album>> getAlbums() {
        MutableLiveData<List<Album>> albumsData = new MutableLiveData<>();
        albums = new ArrayList<>();

        client.getAlbums().enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                if (response.isSuccessful()) {
                    albums = response.body();
                    albumsData.setValue(albums);
                    insertAlbums(albums);
                }

                else {
                    albums = albumDao.getAll();
                    albumsData.setValue(albums);
                }
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                albums = albumDao.getAll();
                albumsData.setValue(albums);
            }
        });

        return albumsData;
    }
}
