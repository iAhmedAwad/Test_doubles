package com.trianglz.test_doubles.common.posts.data.repository

import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class PostsRepoImplTestFake {

    @Test
    fun getPosts_returns_expected_value() {
        runBlockingTest {
            val fakePostRepo = FakePostsRepo()
            val result = fakePostRepo.getPosts()
            assertThat(result).doesNotContain(null)
            assertThat(result).containsNoDuplicates()
        }

    }
}