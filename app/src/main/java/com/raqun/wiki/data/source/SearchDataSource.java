package com.raqun.wiki.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.raqun.wiki.data.Page;
import com.raqun.wiki.data.Query;
import com.raqun.wiki.data.Result;

import rx.Observable;

/**
 * Created by tyln on 16.08.16.
 */
public interface SearchDataSource {
    Observable<Page> search(@NonNull String query);

    void save(@NonNull String query, @NonNull Page page);
}
