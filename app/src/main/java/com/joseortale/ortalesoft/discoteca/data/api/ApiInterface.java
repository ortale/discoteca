package com.joseortale.ortalesoft.discoteca.data.api;

import com.joseortale.ortalesoft.discoteca.model.Album;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("albums")
    Call<List<Album>> getAlbums();
}
