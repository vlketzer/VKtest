package com.example.vktest;



import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.vktest.VKmodel.VKItems;
import com.example.vktest.VKmodel.VKsize;


import hu.akarnokd.rxjava2.math.MathObservable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;




public class VKpresenter {

    private ViewContract uView;


    public void attachView(ViewContract usersActivity) {
        this.uView = usersActivity;
    }

    public void viewIsReady() {
        getPosts();
    }

    public void getPosts(){
        try
        {

            uView.showProgressBar();


            //start observable request to vk
            Disposable dispose = new rxVKdataService().getResponseBody().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(vKjsonResponse -> {
                        if (vKjsonResponse.body() != null && !vKjsonResponse.body().equals(null)) {
                            //catch successed answer with an internal server error (bad request)
                            if (vKjsonResponse.body().getError() != null && !vKjsonResponse.body().getError().equals(null)) {

                                uView.showPostText(vKjsonResponse.body().getError().getErrMsg());
                            } else {

                            //catch successed answer with a usable data
                                @SuppressLint("CheckResult")
                                        //get each item from json request
                                Disposable dispose1 = Observable.fromIterable(vKjsonResponse.body().getResponse().getItems())
                                        .map(item -> {
                                            //define newest(max) post date
                                            Observable<Integer> latestItemDate = MathObservable.max(Observable.fromIterable(vKjsonResponse.body().getResponse().getItems()).map(VKItems::getDate));

                                            latestItemDate.subscribe(gettedLatestItemDate -> {

                                                //if item has an attachment
                                                if (item.getAttachments() != null && !item.getAttachments().equals(null)) {

                                                        //get the size of catched item if the type of nested attachment is PHOTO
                                                        Observable<VKsize> size = Observable.fromIterable(item.getAttachments()).filter(attach -> attach.getType().equals("photo")).flatMapIterable(n -> n.getPhoto().getSizes());

                                                        size.map(takenSize -> {
                                                            //get the max width of each item
                                                            Observable<Integer> maxWidth = MathObservable.max(Observable.just(takenSize).map(VKsize::getWidth));
                                                            maxWidth.subscribe(gettedMaxWidth -> {
                                                                //get url of the picture with a max width
                                                                Observable<String> url = Observable.just(takenSize).filter(sizes -> sizes.getWidth() == gettedMaxWidth).map(VKsize::getUrl);
                                                                url.map(picUrl -> {

                                                                    //start request based on getted url
                                                                    new rxVKdataService().getRxImage(picUrl).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(image -> {
                                                                        //load getted image to bitmap and set text to according label on the main view
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

                                                }
                                                else

                                                    //show post text without any pictures
                                                    uView.showPostText(item.getText());

                                            });
                                            return item;
                                        }).subscribe();
                            }
                        }
                    });



        }
        catch (Exception e){
            //catch some possible errors
            uView.hideProgressBar();
            uView.showPostText(e.getMessage());
        }
    }

    public void detachView() {
        uView = null;
    }


}
