package com.joseortale.ortalesoft.discoteca.data.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.joseortale.ortalesoft.discoteca.model.Album;

import java.util.List;

@Dao
public interface AlbumDao {
    @Query("SELECT * FROM album")
    List<Album> getAll();

    @Query("SELECT * FROM album WHERE id IN (:id)")
    List<Album> loadAllByIds(int[] id);

    @Insert
    void insertAll(Album... albums);

    @Update
    void update(Album album);
}
