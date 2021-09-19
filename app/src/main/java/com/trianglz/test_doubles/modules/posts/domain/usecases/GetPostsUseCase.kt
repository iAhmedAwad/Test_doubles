package com.trianglz.test_doubles.modules.posts.domain.usecases

import com.trianglz.test_doubles.modules.posts.domain.models.PostDomainModel
import com.trianglz.test_doubles.modules.posts.domain.repository.PostsRepo
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(private val postsRepo: PostsRepo) {

    suspend fun getPostsList(): List<PostDomainModel> {
        return postsRepo.getPosts()
    }

    suspend fun getFirstPostId(): Int {
        return postsRepo.getPosts()[0].id
    }
}