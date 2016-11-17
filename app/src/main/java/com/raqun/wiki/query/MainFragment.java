package com.raqun.wiki.query;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.raqun.wiki.R;
import com.raqun.wiki.BaseFragment;
import com.raqun.wiki.search.SearchActivity;
import com.raqun.wiki.utils.AlertUtils;

import java.util.List;

/**
 * Created by tyln on 19.09.16.
 */
public class MainFragment extends BaseFragment implements MainContract.View {
    @NonNull
    private MainContract.Presenter mPresenter;

    @NonNull
    private RecyclerView mRecyclerViewHistory;

    @Nullable
    private QueryAdapter mQueryAdapter;

    @NonNull
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_main;
    }

    @Override
    protected int getMenuRes() {
        return R.menu.menu_main;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (view == null) return;

        mRecyclerViewHistory = (RecyclerView) view.findViewById(R.id.recyclerview_history);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViewHistory.setLayoutManager(linearLayoutManager);
        //mRecyclerViewHistory.addItemDecoration(new DividerDecorator(getActivity()));
        mRecyclerViewHistory.setHasFixedSize(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.start();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        mPresenter.unsubscribe();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mPresenter.destroy();
        super.onDestroy();
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint(getString(R.string.action_search));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                startActivity(SearchActivity.newIntent(getActivity(), query));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mPresenter.searchHistory(newText);
                return false;
            }
        });
    }

    @Override
    public void setPresenter(@NonNull MainContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @UiThread
    @Override
    public void alert(@Nullable String message) {
        AlertUtils.alert(getActivity().getApplicationContext(), message);
    }

    @UiThread
    @Override
    public void initHistory(@NonNull List<String> queries) {
        mQueryAdapter = new QueryAdapter(queries);
        mRecyclerViewHistory.setAdapter(mQueryAdapter);
    }

    @UiThread
    @Override
    public void notifyHistoryChange() {
        mQueryAdapter.notifyDataSetChanged();
    }
}
