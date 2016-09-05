package com.raqun.wiki.data.source.remote;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;

import com.raqun.wiki.api.WikiServices;
import com.raqun.wiki.data.Page;
import com.raqun.wiki.data.Query;
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
public class SearchRemoteDataSource implements SearchDataSource {
    @NonNull
    private final WikiServices mWikiServices;

    public SearchRemoteDataSource(@NonNull WikiServices wikiServices) {
        this.mWikiServices = wikiServices;
    }

    @Override
    public Observable<Page> search(@NonNull String query) {
        return mWikiServices.search(query).map(new Func1<Result, Page>() {
            @Override
            public Page call(Result result) {
                final List<Page> pages = new ArrayList<>(result.getQuery().getPages().values());
                return pages.get(0);
            }
        });
    }

    @Override
    public void save(@NonNull String query, @NonNull Page page) {
        // Empty method
    }
}
