package com.raqun.wiki.data.source.local;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.support.compat.BuildConfig;
import android.util.Log;

import com.raqun.wiki.data.Page;
import com.raqun.wiki.data.source.SearchDataSource;

import java.util.concurrent.ExecutionException;

import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

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

    @WorkerThread
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
                    if (BuildConfig.DEBUG) {
                        Log.i("data from", "realm");
                    }
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
        realm.copyToRealmOrUpdate(realmPage);
        realm.commitTransaction();
        realm.close();
    }
}
