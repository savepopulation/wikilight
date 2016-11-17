package com.raqun.wiki;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.raqun.wiki.Constants;
import com.raqun.wiki.R;

/**
 * Created by tyln on 21.08.16.
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected static final int NAVIGATION_ROOT = 0;
    protected static final int NAVIGATION_BACK = 1;

    @LayoutRes
    protected abstract int getLayoutRes();

    @MenuRes
    protected abstract int getMenuRes();

    protected abstract int getNavigationType();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        setupActionBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getMenuRes() != Constants.NO_RES) {
            getMenuInflater().inflate(getMenuRes(), menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @UiThread
    protected final void setActionbarTitle(@Nullable CharSequence title) {
        if (!TextUtils.isEmpty(title)) {
            final ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setTitle(title);
            }
        }
    }

    private void setupActionBar() {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            switch (getNavigationType()) {
                case NAVIGATION_BACK:
                    actionBar.setDisplayHomeAsUpEnabled(true);
                    break;

                default:
                    actionBar.setDisplayHomeAsUpEnabled(false);
                    break;
            }

        }
    }
}
