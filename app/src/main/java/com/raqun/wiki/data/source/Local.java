package com.raqun.wiki.data.source;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by tyln on 16.08.16.
 */
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
@interface Local {

}
