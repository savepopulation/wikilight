package com.raqun.wiki.utils;

import android.support.annotation.NonNull;

import com.raqun.wiki.BuildConfig;
import com.raqun.wiki.Constants;


/**
 * Created by tyln on 1.09.16.
 */
public final class Utils {
    private Utils() {

    }

    @NonNull
    public static String generateUserAgentForWikiApi() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Constants.APP_NAME);
        stringBuilder.append("/");
        stringBuilder.append(BuildConfig.VERSION_NAME);
        stringBuilder.append(" (");
        stringBuilder.append(Constants.GITHUB_URL);
        stringBuilder.append("/;");
        stringBuilder.append(Constants.AUTHOR_EMAIL);
        stringBuilder.append(") Android");
        return stringBuilder.toString();
    }
}
