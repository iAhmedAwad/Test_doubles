package com.trianglz.test_doubles.modules.posts.data.repository

import com.trianglz.test_doubles.modules.posts.domain.models.PostDomainModel
import com.trianglz.test_doubles.modules.posts.domain.repository.PostsRepo
import junit.framework.TestCase

class PostsRepoImplTestSpy : PostsRepo {
    private var addCalled = 0
    private var lastPostAdded: PostDomainModel? = null

    private val list = ArrayList<PostDomainModel>()

    override suspend fun addPost(post: PostDomainModel) {
        list.add(post)
        addCalled++
        lastPostAdded = post
    }

    override suspend fun getPosts(): List<PostDomainModel> {
        return list
    }

    fun getTimesCalled(): Int {
        return addCalled
    }

    fun getLastPostAdded(): PostDomainModel? {
        return lastPostAdded
    }
}