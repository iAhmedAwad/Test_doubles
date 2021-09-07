package com.trianglz.test_doubles.common.di

import com.trianglz.test_doubles.common.posts.di.PostsFragmentProvider
import com.trianglz.test_doubles.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = [PostsFragmentProvider::class]
    )
    abstract fun contributeUsersMainActivity(): MainActivity

}