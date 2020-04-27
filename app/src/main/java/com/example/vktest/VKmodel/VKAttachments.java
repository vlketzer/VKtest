package com.example.vktest.VKmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



/*
"attachments": [{
"type": "photo",
"photo": {
"id": 456240250,
"album_id": -7,
"owner_id": -857049,
"user_id": 100,
"sizes": [{
"type": "m",
"url": "https://pp.userap...2bf/fCCufMSF7tQ.jpg",
"width": 130,
"height": 93
}...],
 */

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
