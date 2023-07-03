package com.example.homework.data.api.repository

import com.example.homework.data.api.UserPostApi
import com.example.homework.data.api.model.post.Post
import com.example.homework.data.api.model.user.User
import javax.inject.Inject

class UserPostRepository @Inject constructor(
    private val userPostApi: UserPostApi
) {
    suspend fun getPost(): List<Post> {
        return userPostApi.getPost()
    }

    suspend fun getUser(id: String): User {
        return userPostApi.getUser(id)
    }
}