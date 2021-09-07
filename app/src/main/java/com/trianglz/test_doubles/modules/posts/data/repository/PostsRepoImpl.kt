package com.trianglz.test_doubles.modules.posts.data.repository

import com.trianglz.test_doubles.modules.posts.data.service.PostsService
import com.trianglz.test_doubles.modules.posts.domain.models.PostDomainModel
import com.trianglz.test_doubles.modules.posts.domain.repository.PostsRepo
import javax.inject.Inject

class PostsRepoImpl @Inject constructor(private val service: PostsService) : PostsRepo {


    override suspend fun addPost(post: PostDomainModel) {
        throw AssertionError("Method not yet implemented")
    }

    override suspend fun getPosts(): List<PostDomainModel> {
        throw Exception("Method is not implemented yet!")
    }

    companion object {
        private const val TAG = "PostsRepoImpl"
    }
}