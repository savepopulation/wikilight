package com.raqun.wiki.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.raqun.wiki.Constants;
import com.raqun.wiki.R;
import com.raqun.wiki.WikiApplication;
import com.raqun.wiki.ui.BaseActivity;
import com.raqun.wiki.ui.search.SearchActivity;
import com.squareup.haha.perflib.Main;

import javax.inject.Inject;

/**
 * Created by tyln on 19.09.16.
 */
public final class MainActivity extends BaseActivity {
    @NonNull
    @Inject
    MainPresenter mMainPresnter;

    @NonNull
    public static Intent newIntent(@NonNull Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected int getMenuRes() {
        return Constants.NO_RES;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.framelayout_main);
        if (mainFragment == null) {
            mainFragment = MainFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.framelayout_main, mainFragment)
                    .commit();
        }

        DaggerMainComponent.builder()
                .mainPresenterModule(new MainPresenterModule(mainFragment))
                .searchRepositoryComponent(((WikiApplication) getApplication()).getSearcRepositoryComponent())
                .build()
                .inject(this);
    }
}
