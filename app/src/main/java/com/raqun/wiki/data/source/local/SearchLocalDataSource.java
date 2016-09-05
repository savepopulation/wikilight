package com.raqun.wiki.data.source.local;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.raqun.wiki.data.Page;
import com.raqun.wiki.data.Query;
import com.raqun.wiki.data.Result;
import com.raqun.wiki.data.source.SearchDataSource;

import java.util.concurrent.Callable;

import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by tyln on 16.08.16.
 */

@Singleton
public class SearchLocalDataSource implements SearchDataSource {
    @NonNull
    private Context mContext;

    public SearchLocalDataSource(@NonNull Context context) {
        this.mContext = context;
        setupRealm();
    }

    private Realm getRealmInstance() {
        return Realm.getDefaultInstance();
    }

    private void setupRealm() {
        final RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(mContext).build();
        Realm.deleteRealm(realmConfiguration);
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    @Override
    public Observable<Page> search(@NonNull final String query) {
        final Realm realm = getRealmInstance();
        return realm.where(Page.class)
                .equalTo("query", query)
                .findFirstAsync()
                .asObservable()
                .map(new Func1<RealmObject, Page>() {
                    @Override
                    public Page call(RealmObject realmObject) {
                        return (Page) realmObject;
                    }
                });
    }

    @Override
    public void save(@NonNull String query, @NonNull Page page) {
        final Realm realm = getRealmInstance();
        realm.beginTransaction();
        page.setQuery(query);
        realm.copyToRealm(page);
        realm.commitTransaction();
        realm.close();
    }
}
