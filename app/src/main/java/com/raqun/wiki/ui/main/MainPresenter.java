package com.raqun.wiki.ui.main;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.inject.Inject;

/**
 * Created by tyln on 19.09.16.
 */
class MainPresenter implements MainContract.Presenter {
    @Nullable
    private MainContract.View mView;

    @Inject
    MainPresenter(@NonNull MainContract.View view) {
        this.mView = view;

        mView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void destroy() {
        this.mView = null;
    }
}
