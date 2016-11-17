package com.raqun.wiki.query;

import com.raqun.wiki.data.source.SearchRepositoryComponent;
import com.raqun.wiki.utils.FragmentScoped;

import dagger.Component;

/**
 * Created by tyln on 19.09.16.
 */
@FragmentScoped
@Component(dependencies = SearchRepositoryComponent.class, modules = MainPresenterModule.class)
interface MainComponent {
    void inject(MainActivity activity);
}
