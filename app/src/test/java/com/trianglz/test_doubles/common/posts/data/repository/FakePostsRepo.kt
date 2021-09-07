package com.trianglz.test_doubles.common.posts.data.repository

import com.trianglz.test_doubles.common.posts.domain.models.PostDomainModel
import com.trianglz.test_doubles.common.posts.domain.repository.PostsRepo
import kotlinx.coroutines.delay

class FakePostsRepo : PostsRepo {
    override suspend fun getPosts(): List<PostDomainModel> {
        delay((2 * 60 * 1000).toLong())
        val list = ArrayList<PostDomainModel>()

        repeat(10) {
            val postDomainModel = PostDomainModel(
                id = it, title = "Post number: $it",
                body = "Body of post number: $it"
            )
            list.add(postDomainModel)
        }

        return list
    }
}