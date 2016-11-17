package com.raqun.wiki;

import android.support.annotation.Nullable;

/**
 * Created by tyln on 21.08.16.
 */
public interface BaseView<T extends BasePresenter> {
    void setPresenter(T presenter);

    void alert(@Nullable String message);
}
