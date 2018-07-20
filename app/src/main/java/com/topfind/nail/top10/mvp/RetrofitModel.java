package com.topfind.nail.top10.mvp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.topfind.nail.top10.api.SearchApi;
import com.topfind.nail.top10.entity.Item;
import com.topfind.nail.top10.models.ResponseModel;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitModel implements FindContract.IRetrofitModel {

    private static SearchApi searchApi;
    private Retrofit retrofit;

    public RetrofitModel(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
             retrofit = new Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/customsearch/v1/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }


    @Override
    public void GetData(String searchName, final OnFinishedListener onFinishedListener) {
        searchApi = retrofit.create(SearchApi.class);
        searchApi.getData(searchName).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                onFinishedListener.onFinished(response.body().items);
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                onFinishedListener.onFail(t);
            }
        });
    }
}
