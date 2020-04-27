package com.example.vktest.VKmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VKItems {

    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("attachments")
    @Expose
    private List<VKAttachments> attachments;

    @SerializedName("date")
    @Expose
    private Integer date;

    @SerializedName("is_pinned")
    @Expose
    private Integer isPinned;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<VKAttachments> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<VKAttachments> attachments) {
        this.attachments = attachments;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Integer getIsPinned() {
        return isPinned;
    }

    public void setIsPinned(Integer isPinned) {
        this.isPinned = isPinned;
    }

}
