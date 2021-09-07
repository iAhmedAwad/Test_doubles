package com.trianglz.test_doubles.modules.posts.domain.repository

import com.trianglz.test_doubles.modules.posts.domain.models.PostDomainModel

interface PostsRepo {
  suspend  fun getPosts():List<PostDomainModel>
}