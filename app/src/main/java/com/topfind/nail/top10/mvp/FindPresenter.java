package com.topfind.nail.top10.mvp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.topfind.nail.top10.database.TopDatabase;
import com.topfind.nail.top10.entity.Item;
import com.topfind.nail.top10.models.ResponseModel;

import java.util.ArrayList;
import java.util.List;

public class FindPresenter implements FindContract.IPresenter , FindContract.IRetrofitModel.OnFinishedListener {

    private FindContract.IView iView;
    private FindContract.IRetrofitModel iRetrofitModel;
    private FindContract.IRoomModel iRoomModel;

    public FindPresenter(FindContract.IView iView, FindContract.IRetrofitModel iRetrofitModel, FindContract.IRoomModel iRoomModel){
        this.iView = iView;
        this.iRetrofitModel = iRetrofitModel;
        this.iRoomModel = iRoomModel;
    }

    @Override
    public void LoadDataBase() {
        iRoomModel.LoadDataBase(new RoomModel.LoadUserCallback() {
            @Override
            public void onLoad(ArrayList<Item> users) {
                iView.setDataToListView(users);
            }
        });
    }

    @Override
    public void SaveDataBase(ArrayList<Item> items) {
        iRoomModel.SaveDataBase(items);
    }

    @Override
    public void processRequest(String queryText) {
        if(iView != null){
            iView.showProgress();
        }
        iRetrofitModel.GetData(queryText,this);
    }

    @Override
    public void onDestroy() {
        iView = null;
    }

    @Override
    public void onFinished(ArrayList<Item> items) {
        if(iView != null & items != null){
            iView.setDataToListView(items);
            SaveDataBase(items);
            iView.hideProgress();
        }
    }

    @Override
    public void onFail(Throwable throwable) {
        if(iView != null){
            iView.onResponseFailter(throwable);
            iView.hideProgress();
        }
    }


}
