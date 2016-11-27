package com.raqun.wiki.query;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.text.TextUtilsCompat;
import android.text.TextUtils;
import android.util.Log;

import com.raqun.wiki.data.Page;
import com.raqun.wiki.data.source.SearchRepository;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by tyln on 19.09.16.
 */
class MainPresenter implements MainContract.Presenter {
    @Nullable
    private MainContract.View mView;

    @Nullable
    private String mQuery;

    @NonNull
    private final SearchRepository mSearchRepository;

    @NonNull
    private final CompositeSubscription mCompositeSubscription;

    @NonNull
    private final List<String> mQueries;

    @Inject
    MainPresenter(@NonNull MainContract.View view, @NonNull SearchRepository searchRepository) {
        this.mView = view;
        this.mSearchRepository = searchRepository;
        this.mCompositeSubscription = new CompositeSubscription();
        this.mQueries = new ArrayList<>();

        mView.setPresenter(this);
    }

    @Override
    public void start() {
        mView.initHistory(mQueries);
    }

    @Override
    public void subscribe() {
        searchHistory(mQuery);
    }

    @Override
    public void unsubscribe() {
        mCompositeSubscription.clear();
    }

    @Override
    public void destroy() {
        this.mView = null;
    }

    @Override
    public void searchHistory(@NonNull String query) {
        this.mQuery = query;
        searchHistory();
    }

    @Override
    public void search(@NonNull String query) {
        if (TextUtils.isEmpty(query)) {
            mView.onInvalidQuery();
            return;
        }

        mView.navigateToSearchResult(query);
    }

    private void searchHistory() {
        mCompositeSubscription.clear();
        final Subscription subscription = mSearchRepository.searchHistory(mQuery)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Page>>() {
                    @Override
                    public void onCompleted() {
                        Log.i("Search History", "completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.alert(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Page> pages) {
                        mQueries.clear();
                        for (Page page : pages) {
                            mQueries.add(page.getQuery());
                        }
                        mView.notifyHistoryChange();
                    }
                });
        mCompositeSubscription.add(subscription);
    }
}
