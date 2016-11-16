package com.raqun.wiki.ui.main;

import android.support.annotation.NonNull;

import com.raqun.wiki.ui.BasePresenter;
import com.raqun.wiki.ui.BaseView;
import com.raqun.wiki.ui.search.SearchContract;

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
