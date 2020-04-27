package com.example.vktest;



import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.MainThread;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.vktest.VKmodel.VKAttachments;
import com.example.vktest.VKmodel.VKItems;
import com.example.vktest.VKmodel.VKPhoto;
import com.example.vktest.VKmodel.VKResponse;
import com.example.vktest.VKmodel.VKjson;
import com.example.vktest.VKmodel.VKsize;


import hu.akarnokd.rxjava2.math.MathObservable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class VKpresenter {

    private MainActivity uView;


    public void attachView(MainActivity usersActivity) {
        uView = usersActivity;
    }

    public void viewIsReady() {
        getPosts();
    }

    public void getPosts(){
        try
        {

            uView.showProgressBar();


            Disposable dispose = new rxVKdataService().getResponseBody().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(vKjsonResponse -> {
                        if (vKjsonResponse.body() != null && !vKjsonResponse.body().equals(null)) {
                            if (vKjsonResponse.body().getError() != null && !vKjsonResponse.body().getError().equals(null)) {
                                ShowMessage(vKjsonResponse.body().getError().getErrMsg());
                            } else {


                                @SuppressLint("CheckResult")
                                Disposable dispose1 = Observable.fromIterable(vKjsonResponse.body().getResponse().getItems())
                                        .map(item -> {
                                            Observable<Integer> latestItemDate = MathObservable.max(Observable.fromIterable(vKjsonResponse.body().getResponse().getItems()).map(VKItems::getDate));

                                            latestItemDate.subscribe(gettedLatestItemDate -> {
                                                Observable<VKsize> size = Observable.fromIterable(item.getAttachments()).flatMapIterable(n -> n.getPhoto().getSizes());

                                                size.map(takenSize -> {
                                                    Observable<Integer> maxWidth = MathObservable.max(Observable.just(takenSize).map(VKsize::getWidth));
                                                    maxWidth.subscribe(gettedMaxWidth -> {
                                                        Observable<String> url = Observable.just(takenSize).filter(sizes -> sizes.getWidth() == gettedMaxWidth).map(VKsize::getUrl);
                                                        url.map(picUrl -> {

                                                            new rxVKdataService().getRxImage(picUrl).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(image -> {
                                                                Bitmap bmp = BitmapFactory.decodeStream(image.byteStream());
                                                                if (item.getDate().equals(gettedLatestItemDate)) {
                                                                    uView.hideProgressBar();
                                                                    uView.setImage(bmp);
                                                                    uView.showPostText(item.getText());

                                                                }
                                                            });
                                                            return picUrl;
                                                        }).subscribe();
                                                    });
                                                    return maxWidth;
                                                }).subscribe();
                                            });
                                            return item;
                                        }).subscribe();
                            }
                        }
                    });



        }
        catch (Exception e){
            uView.hideProgressBar();
            uView.showPostText(e.getMessage());
        }
    }

    public void ShowMessage(final String val) {
        uView.showPostText(val);
    }



}
