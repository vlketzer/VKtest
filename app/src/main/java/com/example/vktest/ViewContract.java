package com.example.vktest;

import android.graphics.Bitmap;

public interface ViewContract {

    void setImage(Bitmap img);
    void showPostText(String data);
    void showProgressBar();
    void hideProgressBar();

}
