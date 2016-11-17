package com.raqun.wiki.search;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tyln on 21.08.16.
 */

@Module
class SearchPresenterModule {
    @NonNull
    private final SearchContract.View mView;

    @Nullable
    private final String mQuery;

    SearchPresenterModule(@NonNull SearchContract.View view, @Nullable String query) {
        this.mView = view;
        this.mQuery = query;
    }

    @Provides
    SearchContract.View provideSearchContractView() {
        return mView;
    }

    @Provides
    String provideQuery() {
        return this.mQuery;
    }
}
