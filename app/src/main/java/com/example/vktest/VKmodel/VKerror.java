package com.example.vktest.VKmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class VKerror {

    @SerializedName("error_code")
    @Expose
    private int errCode;

    @SerializedName("error_msg")
    @Expose
    private String errMsg;


    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

}
