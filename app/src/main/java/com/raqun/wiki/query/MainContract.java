package com.raqun.wiki.query;

import android.support.annotation.NonNull;

import com.raqun.wiki.BasePresenter;
import com.raqun.wiki.BaseView;

import java.util.List;

/**
 * Created by tyln on 19.09.16.
 */
interface MainContract {
    interface View extends BaseView<Presenter> {
        void initHistory(@NonNull List<String> queries);

        void notifyHistoryChange();
    }

    interface Presenter extends BasePresenter {
        void start();

        void searchHistory(@NonNull String query);
    }
}
