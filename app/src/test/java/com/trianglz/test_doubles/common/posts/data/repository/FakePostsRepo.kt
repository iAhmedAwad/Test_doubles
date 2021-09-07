package com.trianglz.test_doubles.common.posts.data.repository


import com.trianglz.test_doubles.modules.posts.domain.models.PostDomainModel
import com.trianglz.test_doubles.modules.posts.domain.repository.PostsRepo
import kotlinx.coroutines.delay

class FakePostsRepo : PostsRepo {
    private val list = ArrayList<PostDomainModel>().apply {
        repeat(10) {
            val postDomainModel = PostDomainModel(
                id = it, title = "Post number: $it",
                body = "Body of post number: $it"
            )
            add(postDomainModel)
        }
        val postDomainModel = PostDomainModel(
            id = 3, title = "Post number: 3",
            body = "Body of post number: 3"
        )
        add(postDomainModel)
    }

    override suspend fun getPosts(): List<PostDomainModel> {
        delay((2 * 60 * 1000).toLong())
        return list
    }

    override suspend fun addPost(post: PostDomainModel) {
       list.add(post)
        //list.remove(post)
    }
}