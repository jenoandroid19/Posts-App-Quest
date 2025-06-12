package com.jeno.androidquest1.di

import android.app.Application
import androidx.room.Room
import com.jeno.androidquest1.data.remote.api.PostsAPI
import com.jeno.androidquest1.data.remote.dao.PostsDao
import com.jeno.androidquest1.data.remote.db.PostsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providePostsAPI(): PostsAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build()
            .create(PostsAPI::class.java)
    }

    @Provides
    @Singleton
    fun providePostsDatabase(context: Application): PostsDatabase {
        return Room.databaseBuilder(
            context,
            PostsDatabase::class.java,
            "post_db"
        ).build()
    }

    @Provides
    @Singleton
    fun providePostsDao(database: PostsDatabase): PostsDao {
        return database.postsDao
    }
}