package com.raqun.wiki.search;

import android.support.annotation.NonNull;

import com.raqun.wiki.BasePresenter;
import com.raqun.wiki.BaseView;

/**
 * Created by tyln on 21.08.16.
 */
public interface SearchContract {
    interface View extends BaseView<Presenter> {
        void showSearchResult(@NonNull String result);

        void emptyResult();

        void showSearchIndicator();

        void hideSearchIndicator();
    }

    interface Presenter extends BasePresenter {
        // Marker
    }
}
