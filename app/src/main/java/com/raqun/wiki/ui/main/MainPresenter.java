package com.raqun.wiki.ui.main;

import android.support.annotation.NonNull;

import javax.inject.Inject;

/**
 * Created by tyln on 19.09.16.
 */
public class MainPresenter implements MainContract.Presenter {
    @NonNull
    private final MainContract.View mView;

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
}
