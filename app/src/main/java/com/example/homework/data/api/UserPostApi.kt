package com.example.homework.data.api

import com.example.homework.data.api.model.post.Post
import com.example.homework.data.api.model.user.User
import retrofit2.http.GET
import retrofit2.http.Path

interface UserPostApi {

    @GET("posts")
    suspend fun getPost(): List<Post>

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: String): User
}