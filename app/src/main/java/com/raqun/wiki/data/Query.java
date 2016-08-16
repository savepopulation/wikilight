package com.raqun.wiki.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tyln on 14.08.16.
 */
public class Query {
    @SerializedName("Query")
    private String query;

    public Query(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
