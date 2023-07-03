package com.example.homework.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework.data.userPost.UserPost
import com.example.homework.data.db.UserPostDao
import com.example.homework.data.api.repository.UserPostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userPostRepo: UserPostRepository,
    private val userPostDao: UserPostDao,
) : ViewModel() {
    private val _state = MutableStateFlow(emptyList<UserPost>())
    val state: StateFlow<List<UserPost>>
        get() = _state

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    private val _error = MutableStateFlow(false)
    val error = _error.asStateFlow()

    init {
        fetchUserPostData()
    }

    fun fetchUserPostData() {
        viewModelScope.launch {
            _error.value = false
            val getUserPostData = userPostDao.getUserPostData()
            Log.i("fetchUserPostData", getUserPostData.toString())
            _state.value = getUserPostData
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.value = true
            try {
                val posts = userPostRepo.getPost()
                val updatedState = posts.map { post ->
                    val user = userPostRepo.getUser(post.userId)
                    UserPost(post.title, user.name)
                }
                _state.value = updatedState
                Log.i("refresh", _state.value.toString())
            } catch (e: Exception) {
                _error.value = true
            }
        }
        _isRefreshing.value = false
        _error.value = false
    }
}

