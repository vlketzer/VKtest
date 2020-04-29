package com.example.vktest;

import android.graphics.Bitmap;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity implements ViewContract {

    VKpresenter presenter = new VKpresenter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ( findViewById(R.id.imageView)).setVisibility(View.GONE);
        ( findViewById(R.id.lblPostText)).setVisibility(View.GONE);

        presenter.attachView(this);
        presenter.viewIsReady();

    }


    public void showPostText(String data){
        msg(data);
    }


    public void msg(String val) {
        findViewById(R.id.lblPostText).setVisibility(View.VISIBLE);
        ((TextView) findViewById(R.id.lblPostText)).setText(val);
    }

    public void hideProgressBar(){
        (findViewById(R.id.progressBar)).setVisibility(View.GONE);
    }

    public void showProgressBar(){
        (findViewById(R.id.progressBar)).setVisibility(View.VISIBLE);
    }

    public void setImage(Bitmap img){
        findViewById(R.id.imageView).setVisibility(View.VISIBLE);

        ((ImageView) findViewById(R.id.imageView)).setImageBitmap(img);

        this.setTitle("pic resolution: " + img.getWidth() + "x" + img.getHeight());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
