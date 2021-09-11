package com.trianglz.test_doubles.modules.posts.domain.usecases

import com.trianglz.test_doubles.modules.posts.domain.models.PostDomainModel
import com.trianglz.test_doubles.modules.posts.domain.repository.PostsRepo
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(private val postsRepo: PostsRepo) {

    suspend fun execute(): List<PostDomainModel> {
        return postsRepo.getPosts()
    }

    suspend fun addPost(post: PostDomainModel) {
        //Hypothetical condition
       // if (post.id > 9)
            postsRepo.addPost(post)
    }
}