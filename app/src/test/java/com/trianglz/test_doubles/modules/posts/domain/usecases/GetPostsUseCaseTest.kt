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
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.doNothing

import org.mockito.Mockito.doReturn
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

            //Exception
            // doReturn(posts).`when`(repo.getPosts())
            doReturn(posts).`when`(repo).getPosts()
            assertThat(useCase.execute()).doesNotContain(null)
        }
    }

    //Multiple calls

    @Test
    fun calculatePostsReachSum_sumIsEight_returnsEight() {
        runBlockingTest {

            val post = PostDomainModel(2, "title", "body", 4)

            // val post2 = PostDomainModel(2, "title", "body",3)

            // `when`(repo.getPost(2)).thenReturn(post).thenReturn(post2)
            `when`(repo.getPost(2)).thenReturn(post)

            val result = useCase.calculatePostsReachSum(listOf(2, 2))
            assertThat(result).isEqualTo(8)
            // assertThat(result).isEqualTo(7)
        }
    }

    //Methods with Unit return type
    @Test
    fun addBook() {
        runBlockingTest {
            val post = PostDomainModel(2, "title", "body", 4)
            `when`(repo.addPost(post)).thenReturn(Unit)
            useCase.addPost(post)
        }
    }
}

