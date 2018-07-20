package com.topfind.nail.top10.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "top")
public class Item {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("title")
    @Expose
    @ColumnInfo(name = "title")
    private String title;

    @SerializedName("link")
    @Expose
    @ColumnInfo(name = "link")
    private String link;

    @SerializedName("snippet")
    @Expose
    @ColumnInfo(name = "snippet")
    private String snippet;

    public Item(int id, String title, String link, String snippet) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.snippet = snippet;
    }


    public String getLink() {
        return link;
    }

    public void setLink(String link){
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet){
        this.snippet = snippet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
