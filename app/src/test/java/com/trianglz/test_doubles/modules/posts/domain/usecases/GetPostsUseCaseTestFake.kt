package com.trianglz.test_doubles.modules.posts.domain.usecases

import com.google.common.truth.Truth.assertThat
import com.trianglz.test_doubles.common.posts.data.repository.FakePostsRepo

import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class GetPostsUseCaseTestFake {

    @Test
    fun getPosts() {

        runBlockingTest {

            val fakePostRepo = FakePostsRepo()
            val useCase=GetPostsUseCase(fakePostRepo)
            val result = useCase.execute()
            assertThat(result).containsNoDuplicates()
        }
    }
}