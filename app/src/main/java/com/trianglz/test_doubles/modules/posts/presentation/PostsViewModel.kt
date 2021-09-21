package com.trianglz.test_doubles.modules.posts.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trianglz.test_doubles.modules.posts.domain.models.PostDomainModel
import com.trianglz.test_doubles.modules.posts.domain.usecases.GetPostsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PostsViewModel @Inject constructor(
    private val postsUseCase: GetPostsUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _postsList: MutableLiveData<List<PostDomainModel>> = MutableLiveData()
    val postsList: LiveData<List<PostDomainModel>>
        get() = _postsList



     fun getPosts() {
        viewModelScope.launch(ioDispatcher) {
            _postsList.postValue(postsUseCase.execute())
        }
    }

}