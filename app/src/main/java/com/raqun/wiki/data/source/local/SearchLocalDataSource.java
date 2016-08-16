package com.raqun.wiki.data.source.local;

import android.content.Context;
import android.support.annotation.NonNull;

import com.raqun.wiki.data.Query;
import com.raqun.wiki.data.source.SearchDataSource;

import javax.inject.Singleton;

/**
 * Created by tyln on 16.08.16.
 */

@Singleton
public class SearchLocalDataSource implements SearchDataSource {
    private final Context mContext;

    public SearchLocalDataSource(@NonNull Context context) {
        this.mContext = context;
    }

    @Override
    public void search(Query query) {

    }
}
