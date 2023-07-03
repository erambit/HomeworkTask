package com.example.homework.di

import android.content.Context
import androidx.room.Room
import com.example.homework.data.db.UserPostDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {
    @Provides
    @Singleton
    fun providesDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        UserPostDatabase::class.java,
        "userPost_database"
    )
        .createFromAsset("database/userPost.db")
        .allowMainThreadQueries()
        .build()

    @Singleton
    @Provides
    fun provideDao(database: UserPostDatabase) = database.userDao()

}