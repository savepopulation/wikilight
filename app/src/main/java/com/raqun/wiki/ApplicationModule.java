package com.raqun.wiki;

import android.content.Context;
import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tyln on 16.08.16.
 */

@Module
public class ApplicationModule {
    private final Context mContext;

    ApplicationModule(@NonNull Context context) {
        this.mContext = context;
    }

    @Provides
    Context provideContext() {
        return this.mContext;
    }
}
