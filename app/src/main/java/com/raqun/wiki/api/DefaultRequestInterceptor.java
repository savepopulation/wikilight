package com.raqun.wiki.api;

import com.raqun.wiki.utils.Utils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by tyln on 1.09.16.
 */
public class DefaultRequestInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request request = chain.request().newBuilder()
                .addHeader("User-Agent", Utils.generateUserAgentForWikiApi())
                .build();
        return chain.proceed(request);
    }
}
