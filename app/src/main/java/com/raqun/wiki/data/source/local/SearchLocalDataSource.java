package com.raqun.wiki.data.source.local;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import com.raqun.wiki.data.HistoryItem;
import com.raqun.wiki.data.Page;
import com.raqun.wiki.data.source.SearchDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.Sort;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by tyln on 16.08.16.
 */

@Singleton
public final class SearchLocalDataSource implements SearchDataSource {
    @NonNull
    private Context mContext;

    @NonNull
    private RealmConfiguration mRealmConfiguration;

    public SearchLocalDataSource(@NonNull Context context) {
        this.mContext = context;
        this.mRealmConfiguration = new RealmConfiguration.Builder(mContext).build();
    }

    @Override
    public Observable<Page> search(@NonNull final String query) {
        return Observable.create(new Observable.OnSubscribe<Page>() {
            @Override
            public void call(Subscriber<? super Page> subscriber) {
                final Realm realm = Realm.getInstance(mRealmConfiguration);
                final Page page = realm.where(Page.class)
                        .equalTo("query", query)
                        .findFirst();

                if (page != null && page.isLoaded() && page.isValid()) {
                    subscriber.onNext(realm.copyFromRealm(page));
                } else {
                    Observable.empty();
                }

                subscriber.onCompleted();
                realm.close();
            }
        });
    }

    @WorkerThread
    @Override
    public void save(@NonNull String query, @NonNull Page page) {
        final Realm realm = Realm.getInstance(mRealmConfiguration);
        realm.beginTransaction();

        final Page realmPage = realm.createObject(Page.class);
        realmPage.setQuery(query);
        realmPage.setId(page.getId());
        realmPage.setTitle(page.getTitle());
        realmPage.setContent(page.getContent());
        realmPage.setCreateDate(System.currentTimeMillis());
        realm.copyToRealmOrUpdate(realmPage);
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public Observable<HistoryItem> searchHistory(@Nullable final String query) {
        return Observable.from(getAllHistoryQueries()).filter(new Func1<Page, Boolean>() {
            @Override
            public Boolean call(Page page) {
                return page.getQuery().contains(query);
            }
        }).map(new Func1<Page, HistoryItem>() {
            @Override
            public HistoryItem call(Page page) {
                return new HistoryItem(page.getQuery(), page.getCreateDate());
            }
        });
    }

    @WorkerThread
    private List<Page> getAllHistoryQueries() {
        final Realm realm = Realm.getInstance(mRealmConfiguration);
        realm.beginTransaction();

        final RealmResults<Page> realmResults = realm.where(Page.class)
                .findAllSorted("createDate", Sort.DESCENDING);

        final List<Page> pages = realm.copyFromRealm(realmResults);
        realm.commitTransaction();
        realm.close();

        return pages;
    }
}
