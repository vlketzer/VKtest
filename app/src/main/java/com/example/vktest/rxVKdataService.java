package com.example.vktest;

import com.example.vktest.VKmodel.VKItems;
import com.example.vktest.VKmodel.VKResponse;
import com.example.vktest.VKmodel.VKerror;
import com.example.vktest.VKmodel.VKjson;
import com.example.vktest.VKmodel.VKsize;
import com.example.vktest.Const;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class rxVKdataService {

    private VKapi vkapi;

    rxVKdataService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        vkapi = retrofit.create(VKapi.class);
    }



    Observable<Response<VKjson>> getResponseBody(){
        return vkapi.getRxJsonData1(Const.VK_OWNER_ID, 3, Const.VK_TOKEN, Const.VK_API_VERSION);
    }

    Observable<ResponseBody> getRxImage(String url){
        return vkapi.getRxImage(url);
    }







}
