package com.raqun.wiki.ui.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.raqun.wiki.R;
import com.raqun.wiki.WikiApplication;
import com.raqun.wiki.ui.BaseActivity;

import javax.inject.Inject;

public class SearchActivity extends BaseActivity {

    @Inject
    SearchPresenter mSearchPresenter;

    public static Intent newIntent(@NonNull Context context) {
        return new Intent(context, SearchActivity.class);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_search;
    }

    @Override
    protected int getMenuRes() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SearchFragment searchFragment = (SearchFragment) getSupportFragmentManager().findFragmentById(R.id.framelayout_main);
        if (searchFragment == null) {
            searchFragment = SearchFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.framelayout_main, searchFragment)
                    .commit();
        }

        DaggerSearchComponent.builder()
                .searchRepositoryComponent(((WikiApplication) getApplication()).getSearcRepositoryComponent())
                .searchPresenterModule(new SearchPresenterModule(searchFragment))
                .build()
                .inject(this);
    }
}
