package com.trianglz.test_doubles.modules.posts.domain.usecases


import com.google.common.truth.Truth.assertThat
import com.trianglz.test_doubles.modules.posts.domain.models.PostDomainModel
import com.trianglz.test_doubles.modules.posts.domain.repository.PostsRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@ExperimentalCoroutinesApi

class GetPostsUseCaseTest {

    @Rule
    @JvmField
    val rule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var repo: PostsRepo

    @InjectMocks
    private lateinit var useCase: GetPostsUseCase


    // @Before
    //  fun setup() {
    //     MockitoAnnotations.openMocks(this)
    // }

    @Test
    fun execute_containsNoNull_isTrue() {
        runBlockingTest {

            val posts = ArrayList<PostDomainModel>().apply {
                repeat(5) { add(PostDomainModel(1, "title", "body")) }
            }

            `when`(repo.getPosts()).thenReturn(posts)

            assertThat(useCase.execute()).doesNotContain(null)
        }
    }
}