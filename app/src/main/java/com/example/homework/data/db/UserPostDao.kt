package com.example.homework.data.db

import androidx.room.Dao
import androidx.room.Query
import com.example.homework.data.userPost.UserPost

@Dao
interface UserPostDao {
    @Query(
        "SELECT user.name, post.title FROM user " +
                "INNER JOIN post " +
                "WHERE user.id = post.userId"
    )
    fun getUserPostData(): List<UserPost>
}
