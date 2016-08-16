package com.raqun.wiki.data.source;

import com.raqun.wiki.data.Query;
import com.raqun.wiki.data.Result;

import rx.Observable;

/**
 * Created by tyln on 16.08.16.
 */
public interface SearchDataSource {
    void search(Query query);
}
