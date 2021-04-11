package com.joseortale.ortalesoft.discoteca.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class Album {
    @SerializedName("id")
    @Expose
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @SerializedName("userId")
    @Expose
    @ColumnInfo(name = "user_id")
    private Integer userId;

    @SerializedName("title")
    @Expose
    @ColumnInfo(name = "title")
    private String title;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
