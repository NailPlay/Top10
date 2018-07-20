package com.topfind.nail.top10.mvp;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.topfind.nail.top10.database.TopDatabase;
import com.topfind.nail.top10.entity.Item;

import java.util.ArrayList;

public class RoomModel implements FindContract.IRoomModel {
    private static TopDatabase topDatabase;

    public RoomModel(Context context){
        topDatabase =  TopDatabase.getInstance(context);
    }



    @Override
    public void LoadDataBase(LoadUserCallback callback ) {
        LoadItemsTask loadUsersTask = new LoadItemsTask(callback);
        loadUsersTask.execute();
    }

    @Override
    public void SaveDataBase(@NonNull final ArrayList<Item> lists) {
        SaveItemsTask saveItemsTask = new SaveItemsTask(lists);
        saveItemsTask.execute();
    }


    private static class SaveItemsTask extends AsyncTask<Void, Void, Void> {

        private final ArrayList<Item> lists;

        SaveItemsTask(ArrayList<Item> lists) {
            this.lists = lists;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            topDatabase.clearAllTables();
            topDatabase.getItemDao().insertAll(lists);
            return null;
        }
    }




    private static class LoadItemsTask extends AsyncTask<Void, Void, ArrayList<Item>> {

        private final LoadUserCallback  callback;

        LoadItemsTask(LoadUserCallback  callback) {
            this.callback = callback;
        }

        @Override
        protected ArrayList<Item> doInBackground(final Void... params) {
            return  new ArrayList<Item>(topDatabase.getItemDao().getAll());
        }

        @Override
        protected void onPostExecute(ArrayList<Item> item) {
            if (callback != null) {
                callback.onLoad(item);
            }
        }
    }
}
