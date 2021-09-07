package com.trianglz.test_doubles.common.posts.di

import com.trianglz.test_doubles.common.posts.presentation.PostsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class PostsFragmentProvider {

    @PostsScope
    @ContributesAndroidInjector(modules = [PostsModule::class, PostsViewModelModule::class])
    abstract fun providePostsFragment(): PostsFragment

}

