package com.trianglz.test_doubles.modules.posts.domain.usecases

import com.trianglz.test_doubles.modules.posts.domain.models.PostDomainModel
import com.trianglz.test_doubles.modules.posts.domain.repository.PostsRepo
import javax.inject.Inject

open class GetPostsUseCase @Inject constructor(private val postsRepo: PostsRepo) {

suspend fun execute(): List<PostDomainModel> {
    return postsRepo.getPosts()
}

}