package com.raqun.wiki.util;

import android.support.annotation.Nullable;

/**
 * Created by tyln on 30/03/2017.
 */

public final class ValidationUtil {
    private ValidationUtil() {
        // Private Emptry Constructor
    }

    public static boolean isNullOrEmpty(@Nullable String s) {
        return s == null || s.length() == 0 || s.trim().length() == 0;
    }
}
