package com.example.vktest;

import com.example.vktest.VKmodel.VKjson;


import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class rxVKdataService {

    private VKapi vkapi;


    //let's init hte retrofit
    rxVKdataService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        vkapi = retrofit.create(VKapi.class);
    }


    //let's get the body of the json request
    Observable<Response<VKjson>> getResponseBody(){
        return vkapi.getRxJsonData(Const.VK_OWNER_ID, Const.VK_POSTS_COUNT, Const.VK_TOKEN, Const.VK_API_VERSION);
    }

    //let's get the body of request of the picture
    Observable<ResponseBody> getRxImage(String url){
        return vkapi.getRxImage(url);
    }







}
