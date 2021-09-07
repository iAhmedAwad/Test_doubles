package com.trianglz.test_doubles.common.posts.data.repository

import android.util.Log
import com.haroldadmin.cnradapter.NetworkResponse
import com.haroldadmin.cnradapter.executeWithRetry
import com.trianglz.test_doubles.common.posts.data.service.PostsService
import com.trianglz.test_doubles.common.posts.data.mapper.toPostDomainModel
import com.trianglz.test_doubles.common.posts.domain.models.PostDomainModel
import com.trianglz.test_doubles.common.posts.domain.repository.PostsRepo
import javax.inject.Inject

class PostsRepoImpl @Inject constructor(private val service: PostsService):PostsRepo {
    override suspend fun getPosts(): List<PostDomainModel> {

        val result = executeWithRetry(5){
            service.getPosts()
        }
        when(result){
            is NetworkResponse.Success -> {
                return result.body.map { postDataResponseItem ->
                    postDataResponseItem.toPostDomainModel() }
            }
            is NetworkResponse.NetworkError -> {
                Log.d(TAG, "NetworkError: Error: ${result.error.message}")
                return emptyList()
            }

            is NetworkResponse.ServerError -> {
                Log.d(TAG, "ServerError: Error: ${result.body}")
                return emptyList()

            }
            is NetworkResponse.UnknownError -> {
                Log.d(TAG, "UnknownError: Error: ${result.error.message}")
                return emptyList()

            }
        }
    }

    override suspend fun addPost(postDomainModel: PostDomainModel) {
        throw Exception("Method not yet implemented!")
    }

    companion object{
        private const val TAG = "PostsRepoImpl"
    }
}