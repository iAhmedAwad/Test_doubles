package com.trianglz.test_doubles.common.posts.di

import com.trianglz.test_doubles.common.posts.data.service.PostsService
import com.trianglz.test_doubles.common.posts.data.repository.PostsRepoImpl
import com.trianglz.test_doubles.common.posts.data.repository.UselessRepoImpl
import com.trianglz.test_doubles.common.posts.domain.repository.PostsRepo
import com.trianglz.test_doubles.common.posts.domain.repository.UselessRepo
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
abstract class PostsModule {

    @PostsScope
    @Binds
    abstract fun bindPostsRepo(postsRepoImpl: PostsRepoImpl): PostsRepo

    @PostsScope
    @Binds
    abstract fun bindUselessRepo(uselessRepoImpl: UselessRepoImpl): UselessRepo

    companion object {
        @PostsScope
        @Provides
        fun providePostsService(
            retrofit: Retrofit
        ): PostsService = retrofit.create(PostsService::class.java)
    }
}