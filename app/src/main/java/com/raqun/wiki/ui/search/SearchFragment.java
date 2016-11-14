package com.raqun.wiki.ui.search;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;

import com.raqun.wiki.R;
import com.raqun.wiki.ui.BaseFragment;
import com.raqun.wiki.utils.AlertUtils;

/**
 * Created by tyln on 21.08.16.
 */
public class SearchFragment extends BaseFragment implements SearchContract.View {
    @NonNull
    private SearchContract.Presenter mPresenter;

    @NonNull
    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_search;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
    }

    @Override
    public void setPresenter(@NonNull SearchContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @UiThread
    @Override
    public void onDefaultMessage(@Nullable String message) {
        AlertUtils.alert(getActivity(), message);
    }
}
