package com.raqun.wiki.ui.search;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;

import com.raqun.wiki.R;
import com.raqun.wiki.ui.BaseFragment;
import com.raqun.wiki.utils.AlertUtils;

import org.w3c.dom.Text;

/**
 * Created by tyln on 21.08.16.
 */
public class SearchFragment extends BaseFragment implements SearchContract.View {
    @NonNull
    private SearchContract.Presenter mPresenter;

    @NonNull
    private TextView mTextViewResult;

    @NonNull
    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_search;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (view == null) return;

        mTextViewResult = (TextView) view.findViewById(R.id.textview_result);
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
    public void setPresenter(@NonNull SearchContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @UiThread
    @Override
    public void onDefaultMessage(@Nullable String message) {
        AlertUtils.alert(getActivity(), message);
    }

    @UiThread
    @Override
    public void showSearchResult(@NonNull String result) {
        mTextViewResult.setText(result);
    }

    @UiThread
    @Override
    public void emptyResult() {
        AlertUtils.alert(getActivity().getApplicationContext(),getString(R.string.error_empty_result));
    }
}
