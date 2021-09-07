package com.trianglz.test_doubles.common.application

import com.trianglz.test_doubles.common.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class BaseApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
       return DaggerAppComponent.builder().bindApplication(this).build()
    }
}