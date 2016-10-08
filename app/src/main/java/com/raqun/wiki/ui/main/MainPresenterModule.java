package com.raqun.wiki.ui.main;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tyln on 19.09.16.
 */

@Module
public class MainPresenterModule {
    private final MainContract.View mView;

    MainPresenterModule(@NonNull MainContract.View view) {
        this.mView = view;
    }

    @Provides
    MainContract.View provideMainContractView() {
        return this.mView;
    }
}

