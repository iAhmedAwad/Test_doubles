package com.trianglz.test_doubles.common.posts.domain.usecases

import com.trianglz.test_doubles.common.posts.domain.models.PostDomainModel
import com.trianglz.test_doubles.common.posts.domain.repository.PostsRepo
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(private val postsRepo: PostsRepo) {

suspend fun execute(): List<PostDomainModel> {
    return postsRepo.getPosts()
}

}