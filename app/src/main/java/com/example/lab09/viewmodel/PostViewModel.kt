package com.example.lab09.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab09.PostApiService
import com.example.lab09.PostModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostViewModel(private val servicio: PostApiService) : ViewModel() {
    private val _posts = MutableStateFlow<List<PostModel>>(emptyList())
    val posts: StateFlow<List<PostModel>> get() = _posts

    private val _post = MutableStateFlow<PostModel?>(null)
    val post: StateFlow<PostModel?> get() = _post

    fun fetchUserPosts() {
        viewModelScope.launch {
            _posts.value = servicio.getUserPosts()
        }
    }

    fun fetchUserPostById(id: Int) {
        viewModelScope.launch {
            _post.value = servicio.getUserPostById(id)
        }
    }
}
