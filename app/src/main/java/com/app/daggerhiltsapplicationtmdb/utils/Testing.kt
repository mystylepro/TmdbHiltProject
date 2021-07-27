package com.app.daggerhiltsapplicationtmdb.utils

import javax.inject.Qualifier

object Testing
{
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class BaseUrlKey

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class NormalString
}