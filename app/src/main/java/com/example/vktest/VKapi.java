package com.example.vktest;

import com.example.vktest.VKmodel.VKjson;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;


public interface VKapi {



    @GET("wall.get")
    Observable<Response<VKjson>> getRxJsonData(@Query("owner_id") int ownerID, @Query("count") int postsCount, @Query("access_token") String token, @Query("v") String APIversion );

    @GET
    Observable<ResponseBody> getRxImage(@Url String imageURL);


}
