package com.raqun.wiki.data.source.remote;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.compat.BuildConfig;
import android.util.Log;

import com.raqun.wiki.data.HistoryItem;
import com.raqun.wiki.data.api.WikiServices;
import com.raqun.wiki.data.Page;
import com.raqun.wiki.data.Result;
import com.raqun.wiki.data.source.SearchDataSource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by tyln on 16.08.16.
 */

@Singleton
public final class SearchRemoteDataSource implements SearchDataSource {
    @NonNull
    private final WikiServices mWikiServices;

    public SearchRemoteDataSource(@NonNull WikiServices wikiServices) {
        this.mWikiServices = wikiServices;
    }

    @Override
    public Observable<Page> search(@NonNull String query) {
        return mWikiServices.search(query).flatMap(new Func1<Result, Observable<Page>>() {
            @Override
            public Observable<Page> call(Result result) {
                final ArrayList<Page> pages = new ArrayList<>(result.getQuery().getPages().values());
                return Observable.from(pages).first();
            }
        });
    }

    @Override
    public void save(@NonNull String query, @NonNull Page page) {
        // Empty method
    }

    @Nullable
    @Override
    public Observable<HistoryItem> searchHistory(@Nullable String query) {
        // Not implemented
        return null;
    }

    @Nullable
    @Override
    public Observable<HistoryItem> getAllQueryHistory() {
        // Not implemented
        return null;
    }
}
