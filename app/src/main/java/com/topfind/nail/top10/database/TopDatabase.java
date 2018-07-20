package com.topfind.nail.top10.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.topfind.nail.top10.dao.ItemDao;
import com.topfind.nail.top10.entity.Item;

@Database(entities = {Item.class}, version = 1, exportSchema = true)
public abstract class TopDatabase extends RoomDatabase {

    private static final String DB_NAME = "itemDatabase.db";
    private static volatile TopDatabase instance;

    public static synchronized TopDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static TopDatabase create(final Context context) {
        return Room.databaseBuilder(
                context,
                TopDatabase.class,
                DB_NAME).build();
    }

    public abstract ItemDao getItemDao();

}