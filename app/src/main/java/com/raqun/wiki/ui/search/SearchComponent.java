package com.raqun.wiki.ui.search;

import com.raqun.wiki.data.source.SearchRepositoryComponent;
import com.raqun.wiki.utils.FragmentScoped;

import javax.inject.Inject;

import dagger.Component;

/**
 * Created by tyln on 21.08.16.
 */

@FragmentScoped
@Component(dependencies = SearchRepositoryComponent.class, modules = SearchPresenterModule.class)
public interface SearchComponent {
    void inject(SearchActivity activity);
}
