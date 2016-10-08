package com.raqun.wiki.ui.search;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.raqun.wiki.ui.BasePresenter;
import com.raqun.wiki.ui.BaseView;

/**
 * Created by tyln on 21.08.16.
 */
public interface SearchContract {

    interface View extends BaseView<Presenter> {
    }

    interface Presenter extends BasePresenter {

    }
}
