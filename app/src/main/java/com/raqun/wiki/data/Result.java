package com.raqun.wiki.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tyln on 16.08.16.
 */
public class Result {
    @SerializedName("batchcomplete")
    private String result;
    @SerializedName("query")
    private Query query;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }
}
