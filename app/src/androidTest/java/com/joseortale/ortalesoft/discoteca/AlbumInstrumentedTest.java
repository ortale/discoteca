package com.joseortale.ortalesoft.discoteca;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.joseortale.ortalesoft.discoteca.data.database.AlbumDao;
import com.joseortale.ortalesoft.discoteca.data.database.AppDatabase;
import com.joseortale.ortalesoft.discoteca.model.Album;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class AlbumInstrumentedTest {
    private AlbumDao albumDao;
    private AppDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        albumDao = db.albumDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void writeUserAndReadInList() throws Exception {
        Album album = new Album();
        album.setId(1);
        album.setTitle("Back In Black");
        album.setUserId(3);
        albumDao.insertAll(album);

        List<Album> byName = albumDao.loadAllById(1);
        assertThat(byName.get(0).getTitle(), equalTo(album.getTitle()));
    }
}