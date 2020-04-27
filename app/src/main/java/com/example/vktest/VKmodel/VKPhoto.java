package com.example.vktest.VKmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
"id": 456240250,
"album_id": -7,
"owner_id": -857049,
"user_id": 100,
"sizes": [{
"type": "m",
"url": "https://pp.userap...2bf/fCCufMSF7tQ.jpg",
"width": 130,
"height": 93
},
...
}],
"text": "",
"date": 1556719320,
"post_id": 5023,
"access_key": "839fec412e375c4caf"
}
}],
 */

public class VKPhoto {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("album_id")
    @Expose
    private Integer albumId;
    @SerializedName("owner_id")
    @Expose
    private Integer ownerId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("sizes")
    @Expose
    private List<VKsize> sizes = null;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("date")
    @Expose
    private Integer date;
    @SerializedName("access_key")
    @Expose
    private String accessKey;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<VKsize> getSizes() {
        return sizes;
    }

    public void setSizes(List<VKsize> sizes) {
        this.sizes = sizes;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }


}
