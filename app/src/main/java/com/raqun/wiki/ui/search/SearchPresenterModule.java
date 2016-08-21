package com.raqun.wiki.ui.search;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tyln on 21.08.16.
 */

@Module
public class SearchPresenterModule {
    private final SearchContract.View mView;

    public SearchPresenterModule(SearchContract.View view) {
        this.mView = view;
    }

    @Provides
    public SearchContract.View provideSearchContractView() {
        return mView;
    }
}
