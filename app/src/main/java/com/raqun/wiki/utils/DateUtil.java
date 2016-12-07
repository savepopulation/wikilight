package com.raqun.wiki.utils;

import java.text.SimpleDateFormat;

/**
 * Created by tyln on 07/12/2016.
 */

public final class DateUtil {
    private DateUtil() {
        // Empty Private Constructor
    }

    private static final String DEFAULT_DATE_FORMAT = "dd.MM.yyyy - HH:mm";

    public static String convertTime(long timeInMilis) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        return simpleDateFormat.format(timeInMilis);
    }
}
