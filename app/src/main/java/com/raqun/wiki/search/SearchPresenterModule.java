package com.raqun.wiki.search;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tyln on 21.08.16.
 */

@Module
final class SearchPresenterModule {
    @NonNull
    private final SearchContract.View mView;

    @Nullable
    private final String mQuery;

    SearchPresenterModule(@NonNull SearchContract.View view, @Nullable String query) {
        this.mView = view;
        this.mQuery = query;
    }

    @Provides
    @NonNull
    SearchContract.View provideSearchContractView() {
        return mView;
    }

    @Provides
    @Nullable
    String provideQuery() {
        return this.mQuery;
    }
}
