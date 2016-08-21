package com.raqun.wiki;

import android.app.Application;

import com.raqun.wiki.data.source.DaggerSearchRepositoryComponent;
import com.raqun.wiki.data.source.SearchRepository;
import com.raqun.wiki.data.source.SearchRepositoryComponent;
import com.raqun.wiki.data.source.SearchRepositoryModule;

/**
 * Created by tyln on 16.08.16.
 */
public class WikiApplication extends Application {
    private SearchRepositoryComponent mSearcRepositoryComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mSearcRepositoryComponent = DaggerSearchRepositoryComponent.builder()
                .applicationModule(new ApplicationModule((getApplicationContext())))
                .searchRepositoryModule(new SearchRepositoryModule())
                .build();
    }

    public SearchRepositoryComponent getSearcRepositoryComponent() {
        return this.mSearcRepositoryComponent;
    }
}
