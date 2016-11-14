package com.raqun.wiki;

import android.app.Application;

import com.raqun.wiki.api.ApiModule;
import com.raqun.wiki.data.source.DaggerSearchRepositoryComponent;
import com.raqun.wiki.data.source.SearchRepository;
import com.raqun.wiki.data.source.SearchRepositoryComponent;
import com.raqun.wiki.data.source.SearchRepositoryModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by tyln on 16.08.16.
 */
public class WikiApplication extends Application {
    private SearchRepositoryComponent mSearcRepositoryComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mSearcRepositoryComponent = DaggerSearchRepositoryComponent.builder()
                .applicationModule(new ApplicationModule((this)))
                .searchRepositoryModule(new SearchRepositoryModule())
                .apiModule(new ApiModule())
                .build();
    }

    public SearchRepositoryComponent getSearcRepositoryComponent() {
        return this.mSearcRepositoryComponent;
    }
}
