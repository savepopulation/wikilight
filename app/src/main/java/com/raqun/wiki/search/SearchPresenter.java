package com.raqun.wiki.search;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.raqun.wiki.data.Page;
import com.raqun.wiki.data.source.SearchRepository;
import com.raqun.wiki.utils.ValidationUtil;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by tyln on 21.08.16.
 */
class SearchPresenter implements SearchContract.Presenter {
    @Nullable
    private SearchContract.View mView;

    @NonNull
    private final SearchRepository mSearchRepository;

    @NonNull
    private final CompositeSubscription mCompositeSubscription;

    @Nullable
    private final String mQuery;

    @Inject
    SearchPresenter(@NonNull SearchContract.View view, @NonNull SearchRepository searchRepository, @Nullable String query) {
        this.mView = view;
        this.mSearchRepository = searchRepository;
        this.mCompositeSubscription = new CompositeSubscription();
        this.mQuery = query;

        mView.setPresenter(this);
    }

    @Override
    public void start() {
        mView.initViews();
    }

    @Override
    public void subscribe() {
        search();
    }

    @Override
    public void unsubscribe() {
        mCompositeSubscription.clear();
    }

    @Override
    public void destroy() {
        this.mView = null;
    }

    private void search() {
        if (ValidationUtil.isNullOrEmpty(mQuery)) {
            return;
        }

        mCompositeSubscription.clear();
        final Subscription subscription = mSearchRepository.search(mQuery)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Page>() {
                    @Override
                    public void onCompleted() {
                        Log.i("Search Copmpleted", mQuery);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.alert(e.getMessage());
                    }

                    @Override
                    public void onNext(Page page) {
                        mView.hideSearchIndicator();
                        final String content = page.getContent();
                        if (ValidationUtil.isNullOrEmpty(content)) {
                            mView.emptyResult();
                            return;
                        }
                        mView.showSearchResult(content);
                    }
                });

        mView.showSearchIndicator();
        mCompositeSubscription.add(subscription);
    }
}
