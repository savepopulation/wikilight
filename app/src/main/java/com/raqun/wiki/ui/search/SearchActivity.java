package com.raqun.wiki.ui.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.raqun.wiki.R;
import com.raqun.wiki.WikiApplication;
import com.raqun.wiki.ui.BaseActivity;

import javax.inject.Inject;

public class SearchActivity extends BaseActivity {
    private static final String BUNDLE_QUERY = "query";

    @Inject
    SearchPresenter mSearchPresenter;

    @Inject
    String mQuery;

    public static Intent newIntent(@NonNull Context context, @NonNull String query) {
        final Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra(BUNDLE_QUERY, query);
        return intent;
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

        String query = "";
        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            query = bundle.getString(BUNDLE_QUERY, "");
        }

        setActionbarTitle(query);

        SearchFragment searchFragment = (SearchFragment) getSupportFragmentManager().findFragmentById(R.id.framelayout_main);
        if (searchFragment == null) {
            searchFragment = SearchFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.framelayout_main, searchFragment)
                    .commit();
        }

        DaggerSearchComponent.builder()
                .searchRepositoryComponent(((WikiApplication) getApplication()).getSearcRepositoryComponent())
                .searchPresenterModule(new SearchPresenterModule(searchFragment, query))
                .build()
                .inject(this);
    }
}
