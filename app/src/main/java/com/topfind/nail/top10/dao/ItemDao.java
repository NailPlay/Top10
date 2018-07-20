package com.topfind.nail.top10.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.topfind.nail.top10.entity.Item;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ItemDao {

    @Query("SELECT * FROM top")
    List<Item> getAll();

    @Insert
    void insertAll(List<Item> item);
}
