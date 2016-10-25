package com.raqun.wiki.ui.main;

import dagger.Component;

/**
 * Created by tyln on 19.09.16.
 */
@Component(modules = MainPresenterModule.class)
interface MainComponent {
    void inject(MainActivity activity);
}
