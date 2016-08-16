package com.raqun.wiki.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tyln on 16.08.16.
 */
public class Result {
    @SerializedName("Result")
    private String result;

    public Result(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
