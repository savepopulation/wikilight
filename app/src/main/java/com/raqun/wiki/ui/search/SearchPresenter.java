package com.raqun.wiki.ui.search;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.raqun.wiki.data.Page;
import com.raqun.wiki.data.Query;
import com.raqun.wiki.data.Result;
import com.raqun.wiki.data.source.SearchRepository;
import com.raqun.wiki.ui.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
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

    @NonNull
    private String mQuery;

    @Inject
    public SearchPresenter(@NonNull SearchContract.View view, @NonNull SearchRepository searchRepository) {
        this.mView = view;
        this.mSearchRepository = searchRepository;
        this.mCompositeSubscription = new CompositeSubscription();

        mView.setPresenter(this);
    }

    @Override
    public void search(@Nullable String query) {
        this.mQuery = query;
        final Subscription subscription = mSearchRepository.search(this.mQuery)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Page>() {
                    @Override
                    public void onCompleted() {
                        // Completed
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onDefaultMessage(e.getMessage());
                    }

                    @Override
                    public void onNext(Page page) {
                        mView.onDefaultMessage(page.getContent());
                    }
                });

        mCompositeSubscription.add(subscription);
    }

    @Override
    public void subscribe() {
        if (!TextUtils.isEmpty(this.mQuery)) {
            search(this.mQuery);
        }
    }

    @Override
    public void unsubscribe() {
        mCompositeSubscription.clear();
    }

    @Override
    public void setQuery(@NonNull String query) {
        this.mQuery = query;
    }
}
