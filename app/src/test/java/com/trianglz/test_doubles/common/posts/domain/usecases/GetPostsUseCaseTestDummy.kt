package com.trianglz.test_doubles.common.posts.domain.usecases

import com.google.common.truth.Truth.assertThat
import com.trianglz.test_doubles.common.posts.data.repository.FakePostsRepo
import com.trianglz.test_doubles.common.posts.data.repository.UselessRepoImplDummy
import com.trianglz.test_doubles.common.posts.domain.models.PostDomainModel
import com.trianglz.test_doubles.common.posts.domain.repository.PostsRepo
import com.trianglz.test_doubles.common.posts.domain.repository.UselessRepo
import junit.framework.TestCase
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class GetPostsUseCaseTestDummy {

    @Test
    fun getPosts_containsNoDuplicates_isTrue() {

        runBlockingTest {

            val fakePostRepo = FakePostsRepo()
            val uselessRepoImplDummy = UselessRepoImplDummy()
            val useCase = GetPostsUseCase(fakePostRepo, uselessRepoImplDummy)
            val result = useCase.execute()
            assertThat(result).containsNoDuplicates()
        }
    }


    @Test
    fun getPosts_usingMockitoContainsNoDuplicates_isTrue() {

        runBlockingTest {

            val postRepo = mock(PostsRepo::class.java)
            val uselessRepo = mock(UselessRepo::class.java)
            val post = PostDomainModel(1, "title", "body")
            val posts = ArrayList<PostDomainModel>().apply {
                repeat(5) { add(post) }
            }

            `when`(postRepo.getPosts()).thenReturn(posts)


            val useCase = GetPostsUseCase(postRepo, uselessRepo)
            val result = useCase.execute()
            assertThat(result).containsNoDuplicates()
        }
    }
}