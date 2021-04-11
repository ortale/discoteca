package com.joseortale.ortalesoft.discoteca.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.joseortale.ortalesoft.discoteca.model.Album;

@Database(entities = {Album.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AlbumDao albumDao();
}
