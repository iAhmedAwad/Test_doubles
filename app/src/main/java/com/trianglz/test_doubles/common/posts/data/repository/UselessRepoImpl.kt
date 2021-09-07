package com.trianglz.test_doubles.common.posts.data.repository

import com.trianglz.test_doubles.common.posts.domain.repository.UselessRepo
import javax.inject.Inject

class UselessRepoImpl @Inject constructor():UselessRepo {
    override suspend fun doSomethingUseless() {
        throw Exception("Method is not yet implemented!")
    }
}