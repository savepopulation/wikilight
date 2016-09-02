package com.raqun.wiki.data;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Created by tyln on 14.08.16.
 */
public class Query {
    @SerializedName("pages")
    private Map<String, Page> pages;

    public Map<String, Page> getPages() {
        return pages;
    }

    public void setPages(Map<String, Page> pages) {
        this.pages = pages;
    }
}
