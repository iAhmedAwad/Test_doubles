package com.trianglz.test_doubles.modules.posts.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trianglz.test_doubles.modules.posts.domain.models.PostDomainModel
import com.trianglz.test_doubles.modules.posts.domain.usecases.GetPostsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class PostsViewModel @Inject constructor(private val postsUseCase: GetPostsUseCase):ViewModel() {
    private val _postsList: MutableLiveData<List<PostDomainModel>> = MutableLiveData()
    val postsList: LiveData<List<PostDomainModel>>
        get() = _postsList


    init {
        getPosts()
    }

    private fun getPosts() {
        viewModelScope.launch {
            _postsList.postValue(postsUseCase.execute())
        }
    }

}