package com.example.vktest.VKmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VKResponse {

    @SerializedName("items")
    @Expose
    private List<VKItems> items;

    @SerializedName("count")
    @Expose
    private Integer count;

    public List<VKItems> getItems() {
        return items;
    }

    public void setItems(List<VKItems> items) {
        this.items = items;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
