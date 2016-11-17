package com.raqun.wiki;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raqun.wiki.Constants;

/**
 * Created by tyln on 21.08.16.
 */
public abstract class BaseFragment extends Fragment {
    @LayoutRes
    protected abstract int getLayoutRes();

    @MenuRes
    protected abstract int getMenuRes();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getMenuRes() != Constants.NO_RES) {
            setHasOptionsMenu(true);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutRes(), container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (getMenuRes() != Constants.NO_RES) {
            inflater.inflate(getMenuRes(), menu);
        }
    }
}
