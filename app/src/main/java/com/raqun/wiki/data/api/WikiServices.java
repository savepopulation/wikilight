package com.raqun.wiki.data.api;

import com.raqun.wiki.data.Result;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by tyln on 14.08.16.
 */
public interface WikiServices {
    @GET("api.php?format=json&action=query&prop=extracts&exlimit=max&explaintext&exintro")
    Observable<Result> search(@Query("titles") String query);
}
