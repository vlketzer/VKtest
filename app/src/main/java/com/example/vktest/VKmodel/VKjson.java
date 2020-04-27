package com.example.vktest.VKmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VKjson {


    @SerializedName("response")
    @Expose
    private VKResponse response;

    @SerializedName("error")
    @Expose
    private VKerror error;

    public VKResponse getResponse() {
        return response;
    }

    public void setResponse(VKResponse response) {
        this.response = response;
    }

    public VKerror getError() {
        return error;
    }

    public void setError(VKerror error) {
        this.error = error;
    }

}
