package com.trianglz.test_doubles.modules.posts.domain.usecases

import com.trianglz.test_doubles.modules.posts.domain.models.PostDomainModel
import com.trianglz.test_doubles.modules.posts.domain.repository.PostsRepo
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(private val postsRepo: PostsRepo) {

    suspend fun addPost(postDomainModel: PostDomainModel) {
     //   if (postDomainModel.id < 2)
            postsRepo.addPost(postDomainModel)
    }

    suspend fun getPosts(): List<PostDomainModel> {
        return postsRepo.getPosts()
    }
}