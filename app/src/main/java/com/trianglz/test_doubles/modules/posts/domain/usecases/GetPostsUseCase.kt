package com.trianglz.test_doubles.modules.posts.domain.usecases

import com.trianglz.test_doubles.modules.posts.domain.models.PostDomainModel
import com.trianglz.test_doubles.modules.posts.domain.repository.PostsRepo
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(private val postsRepo: PostsRepo) {


    suspend fun execute(): List<PostDomainModel> {
        return postsRepo.getPosts()
    }

    //Should be stateless, just for demonstration
    suspend fun calculatePostsReachSum(ids: List<Int>): Double {
        var sum = 0.0
        ids.forEach {
            sum += postsRepo.getPost(it).reach.toDouble()
        }
        return sum
    }

    suspend fun addPost(post: PostDomainModel) {
        postsRepo.addPost(post)
    }
}