package com.trianglz.test_doubles.modules.posts.di

import androidx.lifecycle.ViewModel
import com.trianglz.test_doubles.modules.posts.presentation.PostsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
abstract class PostsViewModelModule {

    @Binds
    @IntoMap
    @ClassKey(PostsViewModel::class)
    abstract fun bindPostsViewModel(viewModel: PostsViewModel): ViewModel

}