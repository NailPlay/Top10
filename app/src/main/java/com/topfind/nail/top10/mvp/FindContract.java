package com.topfind.nail.top10.mvp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;

import com.topfind.nail.top10.database.TopDatabase;
import com.topfind.nail.top10.entity.Item;
import com.topfind.nail.top10.models.ResponseModel;

import java.util.ArrayList;
import java.util.List;

public interface FindContract {

    // Интерфейс View
    interface IView {
        void showProgress();
        void hideProgress();
        void setDataToListView(ArrayList<Item> dataToAdapterView);
        void onResponseFailter(Throwable throwable);
    }

    // Интерфейс бизнес - логики
    interface  IPresenter{
        void processRequest(String queryText);
        void onDestroy();
        void LoadDataBase();
        void SaveDataBase(ArrayList<Item> items);
    }

    // Интерфейс модели
    interface  IRetrofitModel{
        void GetData(String searchName, OnFinishedListener onFinishedListener);
        interface  OnFinishedListener {
            void onFinished(ArrayList<Item> items);
            void onFail(Throwable throwable);
        }
    }

    // Интерфейс модели
    interface IRoomModel{
        void LoadDataBase(LoadUserCallback callback);
        void SaveDataBase(@NonNull final ArrayList<Item> lists);
        interface LoadUserCallback {
            void onLoad(ArrayList<Item> lists);
        }
    }
}
