package com.trianglz.test_doubles.common.posts.data.repository

import com.trianglz.test_doubles.modules.posts.domain.models.PostDomainModel
import com.trianglz.test_doubles.modules.posts.domain.repository.PostsRepo
import kotlinx.coroutines.delay

class FakePostsRepo : PostsRepo {
    private val list = ArrayList<PostDomainModel>()

    override suspend fun getPosts(): List<PostDomainModel> {
        delay((2 * 60 * 1000).toLong())
        return list
    }

    override suspend fun addPost(post: PostDomainModel) {
       list.add(post)
        //list.remove(post)
    }
}