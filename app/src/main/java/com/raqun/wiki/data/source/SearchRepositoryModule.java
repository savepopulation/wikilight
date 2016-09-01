package com.raqun.wiki.data.source;

import android.content.Context;
import android.support.annotation.NonNull;

import com.raqun.wiki.api.WikiServices;
import com.raqun.wiki.data.source.local.SearchLocalDataSource;
import com.raqun.wiki.data.source.remote.SearchRemoteDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tyln on 16.08.16.
 */
@Module
public class SearchRepositoryModule {

    @Singleton
    @Provides
    @Remote
    public SearchRemoteDataSource provideQueryRemoteDataSource(@NonNull WikiServices wikiServices) {
        return new SearchRemoteDataSource(wikiServices);
    }

    @Singleton
    @Provides
    @Local
    public SearchLocalDataSource proideQueryLocalDataSource(@NonNull Context context) {
        return new SearchLocalDataSource(context);
    }
}
