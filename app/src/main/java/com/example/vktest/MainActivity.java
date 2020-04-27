package com.example.vktest;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    VKpresenter presenter = new VKpresenter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        presenter.attachView(this);
        presenter.viewIsReady();

    }


    public void showPostText(String data){
        msg(data);
    }


    public void msg(final String val) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ((TextView) findViewById(R.id.lblPostText)).append(val +"\r\n");

            }
        });
    }

    public void hideProgressBar(){
                (findViewById(R.id.progressBar)).setVisibility(View.GONE);
    }

    public void showProgressBar(){
        (findViewById(R.id.progressBar)).setVisibility(View.VISIBLE);
    }

    public void setImage(Bitmap img){
        ((ImageView) findViewById(R.id.imageView)).setImageBitmap(img);
    }
}
