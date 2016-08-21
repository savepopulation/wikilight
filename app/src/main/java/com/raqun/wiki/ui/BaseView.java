package com.raqun.wiki.ui;

import android.support.annotation.Nullable;

/**
 * Created by tyln on 21.08.16.
 */
public interface BaseView<T extends BasePresenter> {
    void setPresenter(T presenter);

    void onDefaultMessage(@Nullable String message);
}
