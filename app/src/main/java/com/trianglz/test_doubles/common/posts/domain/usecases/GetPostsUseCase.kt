package com.trianglz.test_doubles.common.posts.domain.usecases

import com.trianglz.test_doubles.common.posts.domain.models.PostDomainModel
import com.trianglz.test_doubles.common.posts.domain.repository.PostsRepo
import com.trianglz.test_doubles.common.posts.domain.repository.UselessRepo
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val postsRepo: PostsRepo,
    private val uselessRepo: UselessRepo
) {

    suspend fun execute(): List<PostDomainModel> {
        return postsRepo.getPosts().toSet().toList()
    }

}