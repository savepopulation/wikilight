package com.raqun.wiki.ui.main;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;

import com.raqun.wiki.R;
import com.raqun.wiki.ui.BaseFragment;
import com.raqun.wiki.utils.AlertUtils;

/**
 * Created by tyln on 19.09.16.
 */
public class MainFragment extends BaseFragment implements MainContract.View {
    @NonNull
    private MainContract.Presenter mPresenter;

    @NonNull
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_main;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
    }

    @Override
    public void setPresenter(@NonNull MainContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @UiThread
    @Override
    public void onDefaultMessage(@Nullable String message) {
        AlertUtils.alert(getActivity().getApplicationContext(), message);
    }
}
