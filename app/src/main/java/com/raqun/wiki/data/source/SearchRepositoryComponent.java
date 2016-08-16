package com.raqun.wiki.data.source;

import com.raqun.wiki.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by tyln on 16.08.16.
 */

@Singleton
@Component(modules = {SearchRepositoryModule.class, ApplicationModule.class})
public interface SearchRepositoryComponent {
    SearchRepository getSearchRepository();
}
