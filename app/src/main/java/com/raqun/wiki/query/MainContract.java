package com.raqun.wiki.query;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;

import com.raqun.wiki.BasePresenter;
import com.raqun.wiki.BaseView;
import com.raqun.wiki.data.HistoryItem;

import java.util.List;

/**
 * Created by tyln on 19.09.16.
 */
interface MainContract {
    interface View extends BaseView<Presenter> {
        void initHistory(@NonNull List<HistoryItem> historyItems);

        void notifyHistoryChange();

        void navigateToSearchResult(@NonNull String query);

        void onInvalidQuery();
    }

    interface Presenter extends BasePresenter {
        void start();

        void searchHistory(@NonNull String query);

        void search(@NonNull String query);
    }
}
