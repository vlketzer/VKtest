package com.example.vktest.VKmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class VKAttachments {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("photo")
    @Expose
    private VKPhoto photo;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public  VKPhoto getPhoto() {
        return photo;
    }

    public void setPhoto(VKPhoto photo) {
        this.photo = photo;
    }




}
