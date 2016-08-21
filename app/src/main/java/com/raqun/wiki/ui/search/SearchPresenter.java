package com.raqun.wiki.ui.search;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.raqun.wiki.data.source.SearchRepository;
import com.raqun.wiki.ui.BasePresenter;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by tyln on 21.08.16.
 */
public class SearchPresenter implements SearchContract.Presenter {
    @NonNull
    private SearchContract.View mView;

    @NonNull
    private SearchRepository mSearchRepository;

    @NonNull
    private CompositeSubscription mCompositeSubscription;

    @Inject
    public SearchPresenter(@NonNull SearchContract.View view, @NonNull SearchRepository searchRepository) {
        this.mView = view;
        this.mSearchRepository = searchRepository;
        this.mCompositeSubscription = new CompositeSubscription();

        mView.setPresenter(this);
    }

    @Override
    public void search(@Nullable String query) {
        // Empty Method
    }

    @Override
    public void onSubscribe() {
        // Load search history
    }

    @Override
    public void onUnsubccribe() {
        // Empty Method
    }
}
