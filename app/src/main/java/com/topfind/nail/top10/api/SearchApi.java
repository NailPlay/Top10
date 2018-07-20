package com.topfind.nail.top10.api;
import com.topfind.nail.top10.models.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchApi {
    @GET("?key=AIzaSyBCA-xYl0-NOo-EDWd_y8zrj2BUYf8l9JA&cx=001489116564918320559:ituk0ub2w28")
    Call<ResponseModel> getData(@Query("q") String searchName);
}
