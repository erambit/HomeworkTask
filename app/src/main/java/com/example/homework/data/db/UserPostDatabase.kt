package com.example.homework.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class, Post::class], version = 1)
abstract class UserPostDatabase : RoomDatabase() {
    abstract fun userDao(): UserPostDao
}