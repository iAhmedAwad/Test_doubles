package com.trianglz.test_doubles.common.posts.domain.usecases

import com.google.common.truth.Truth.assertThat
import com.trianglz.test_doubles.common.posts.data.repository.FakePostsRepo
import com.trianglz.test_doubles.common.posts.data.repository.UselessRepoImplDummy
import junit.framework.TestCase
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class GetPostsUseCaseTestDummy {

    @Test
    fun getPosts() {

        runBlockingTest {

            val fakePostRepo = FakePostsRepo()
            val uselessRepoImplDummy=UselessRepoImplDummy()
            val useCase=GetPostsUseCase(fakePostRepo,uselessRepoImplDummy)
            val result = useCase.execute()
            assertThat(result).containsNoDuplicates()
        }
    }
}