package com.trianglz.test_doubles.modules.posts.di

import com.trianglz.test_doubles.modules.posts.presentation.PostsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class PostsFragmentProvider {

    @PostsScope
    @ContributesAndroidInjector(modules = [PostsModule::class, PostsViewModelModule::class])
    abstract fun providePostsFragment(): PostsFragment

}

