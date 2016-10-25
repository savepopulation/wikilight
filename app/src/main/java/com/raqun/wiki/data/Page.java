package com.raqun.wiki.data;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by tyln on 1.09.16.
 */
public class Page extends RealmObject {
    @PrimaryKey
    private String query;
    @SerializedName("pageid")
    private long id;
    @SerializedName("title")
    private String title;
    @SerializedName("extract")
    private String content;

    public Page() {
    }

    public Page(long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
