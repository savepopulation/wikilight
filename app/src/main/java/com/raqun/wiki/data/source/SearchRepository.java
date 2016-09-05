package com.raqun.wiki.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.raqun.wiki.data.Page;
import com.raqun.wiki.data.Query;
import com.raqun.wiki.data.Result;
import com.raqun.wiki.data.source.local.SearchLocalDataSource;
import com.raqun.wiki.data.source.remote.SearchRemoteDataSource;

import java.util.LinkedHashMap;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by tyln on 16.08.16.
 */

@Singleton
public class SearchRepository implements SearchDataSource {
    @NonNull
    private final SearchRemoteDataSource mSearchRemoteDataSource;

    @NonNull
    private final SearchLocalDataSource mSearchLocalDataSource;

    @Nullable
    private LinkedHashMap<String, Page> mResultCache;

    @Inject
    public SearchRepository(@NonNull @Remote SearchRemoteDataSource searchRemoteDataSource, @NonNull @Local SearchLocalDataSource searchLocalDataSource) {
        this.mSearchRemoteDataSource = searchRemoteDataSource;
        this.mSearchLocalDataSource = searchLocalDataSource;
    }

    @Override
    public Observable<Page> search(@NonNull final String query) {
        final Page result = getCachedResultWithQuery(query);
        if (result != null) {
            return Observable.just(result);
        }

        if (mResultCache == null) {
            mResultCache = new LinkedHashMap<>();
        }

        final Observable<Page> localResult = mSearchLocalDataSource.search(query);
        final Observable<Page> remoteResult = mSearchRemoteDataSource.search(query)
                .doOnNext(new Action1<Page>() {
                    @Override
                    public void call(Page page) {
                        mSearchLocalDataSource.save(query, page);
                        mResultCache.put(query, page);
                    }
                });

        return Observable.concat(localResult, remoteResult)
                .first()
                .map(new Func1<Page, Page>() {
                    @Override
                    public Page call(Page page) {
                        if (page == null) {
                            throw new IllegalArgumentException();
                        }
                        return page;
                    }
                });
    }

    @Override
    public void save(@NonNull String query, @NonNull Page page) {
        // Empty method
    }

    @Nullable
    private Page getCachedResultWithQuery(@NonNull String query) {
        if (mResultCache == null || mResultCache.isEmpty()) {
            return null;
        } else {
            return mResultCache.get(query);
        }
    }
}
