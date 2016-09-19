package com.raqun.wiki.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.raqun.wiki.R;
import com.raqun.wiki.ui.BaseActivity;
import com.squareup.haha.perflib.Main;

import javax.inject.Inject;

/**
 * Created by tyln on 19.09.16.
 */
public class MainActivity extends BaseActivity {

    @Inject
    MainPresenter mMainPresnter;

    public static Intent newIntent(@NonNull Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected int getMenuRes() {
        return R.menu.menu_main;
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
                .build()
                .inject(this);
    }
}
