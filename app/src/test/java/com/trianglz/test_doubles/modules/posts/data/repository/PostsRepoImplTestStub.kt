package com.trianglz.test_doubles.modules.posts.data.repository

import com.trianglz.test_doubles.modules.posts.domain.models.PostDomainModel
import com.trianglz.test_doubles.modules.posts.domain.repository.PostsRepo
import junit.framework.TestCase

class PostsRepoImplTestStub : PostsRepo {
    private val list = ArrayList<PostDomainModel>().apply {
        repeat(20) {
            val post = PostDomainModel(id = it, title = "title #$it", body = "body #$it")
            add(post)
        }
    }

    override suspend fun getPosts(): List<PostDomainModel> {
        return list
    }

    override suspend fun addPost(post: PostDomainModel) {
        list.add(post)
    }

}