package com.trianglz.test_doubles.modules.posts.data.service

import com.haroldadmin.cnradapter.NetworkResponse
import com.trianglz.test_doubles.modules.posts.data.models.response.PostDataResponseItem
import com.trianglz.test_doubles.utils.Constants
import retrofit2.http.GET


interface PostsService {
    @GET(Constants.POSTS)
    suspend fun getPosts(): NetworkResponse<List<PostDataResponseItem>, String>
}