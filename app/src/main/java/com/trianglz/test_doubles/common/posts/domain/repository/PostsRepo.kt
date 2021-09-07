package com.trianglz.test_doubles.common.posts.domain.repository

import com.trianglz.test_doubles.common.posts.domain.models.PostDomainModel

interface PostsRepo {
  suspend  fun getPosts():List<PostDomainModel>
}