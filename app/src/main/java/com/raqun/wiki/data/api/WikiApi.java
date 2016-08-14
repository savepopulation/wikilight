package com.raqun.wiki.data.api;

import com.raqun.wiki.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tyln on 14.08.16.
 */
public class WikiApi {
    private WikiServices mWikiServices;
    private Retrofit mRetrofit;

    public WikiApi() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mWikiServices = mRetrofit.create(WikiServices.class);
    }

    public WikiServices getWikiServices() {
        return mWikiServices;
    }

    public Retrofit getRetrofit() {
        return this.mRetrofit;
    }

    private OkHttpClient getClient() {
        final OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClientBuilder.addInterceptor(interceptor);
        }
        //okHttpClientBuilder.addInterceptor(new RequestInterceptor());
        okHttpClientBuilder.connectTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
        return okHttpClientBuilder.build();
    }
}
