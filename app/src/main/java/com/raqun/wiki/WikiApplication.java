package com.raqun.wiki;

import android.app.Application;
import android.support.annotation.NonNull;

import com.crashlytics.android.Crashlytics;
import com.raqun.wiki.data.api.ApiModule;
import com.raqun.wiki.data.source.DaggerSearchRepositoryComponent;
import com.raqun.wiki.data.source.SearchRepositoryComponent;
import com.raqun.wiki.data.source.SearchRepositoryModule;
import io.fabric.sdk.android.Fabric;

/**
 * Created by tyln on 16.08.16.
 */
public class WikiApplication extends Application {
    @NonNull
    private SearchRepositoryComponent mSearcRepositoryComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        mSearcRepositoryComponent = DaggerSearchRepositoryComponent.builder()
                .applicationModule(new ApplicationModule((this)))
                .searchRepositoryModule(new SearchRepositoryModule())
                .apiModule(new ApiModule())
                .build();
    }

    @NonNull
    public SearchRepositoryComponent getSearcRepositoryComponent() {
        return this.mSearcRepositoryComponent;
    }
}
