package com.raqun.wiki.data;

/**
 * Created by tyln on 07/12/2016.
 */

public class HistoryItem {
    private String query;
    private long createDate;

    public HistoryItem(String query, long createDate) {
        this.query = query;
        this.createDate = createDate;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }
}
