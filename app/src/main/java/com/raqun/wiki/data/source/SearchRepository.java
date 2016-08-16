package com.raqun.wiki.data.source;

import android.support.annotation.NonNull;

import com.raqun.wiki.data.Query;
import com.raqun.wiki.data.source.local.SearchLocalDataSource;
import com.raqun.wiki.data.source.remote.SearchRemoteDataSource;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by tyln on 16.08.16.
 */

@Singleton
public class SearchRepository implements SearchDataSource {
    private final SearchRemoteDataSource mSearchRemoteDataSource;
    private final SearchLocalDataSource mSearchLocalDataSource;

    @Inject
    public SearchRepository(@NonNull @Remote SearchRemoteDataSource searchRemoteDataSource, @NonNull @Local SearchLocalDataSource searchLocalDataSource) {
        this.mSearchRemoteDataSource = searchRemoteDataSource;
        this.mSearchLocalDataSource = searchLocalDataSource;
    }

    @Override
    public void search(Query query) {

    }
}
