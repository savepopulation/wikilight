package com.raqun.wiki.ui.search;

import android.support.annotation.Nullable;

import com.raqun.wiki.R;
import com.raqun.wiki.ui.BaseFragment;
import com.raqun.wiki.utils.AlertUtils;

/**
 * Created by tyln on 21.08.16.
 */
public class SearchFragment extends BaseFragment implements SearchContract.View {
    private SearchContract.Presenter mPresenter;

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
        mPresenter.onSubscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.onUnsubccribe();
    }

    @Override
    public void setPresenter(SearchContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void onDefaultMessage(@Nullable String message) {
        AlertUtils.alert(getActivity(), message);
    }
}
