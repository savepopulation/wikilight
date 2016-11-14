package com.raqun.wiki.data.source;

import android.content.Context;
import android.support.annotation.NonNull;

import com.raqun.wiki.api.WikiServices;
import com.raqun.wiki.data.source.local.SearchLocalDataSource;
import com.raqun.wiki.data.source.remote.SearchRemoteDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by tyln on 16.08.16.
 */
@Module
public class SearchRepositoryModule {
    public SearchRepositoryModule() {

    }

    @Singleton
    @Provides
    @Remote
    SearchRemoteDataSource provideQueryRemoteDataSource(@NonNull WikiServices wikiServices) {
        return new SearchRemoteDataSource(wikiServices);
    }

    @Singleton
    @Provides
    @Local
    SearchLocalDataSource proideQueryLocalDataSource(@NonNull Context context) {
        return new SearchLocalDataSource(context);
    }
}
