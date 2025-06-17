package com.jeno.androidquest1.di

import android.app.Application
import androidx.room.Room
import com.jeno.androidquest1.data.remote.api.ApiService
import com.jeno.androidquest1.data.remote.dao.PostsDao
import com.jeno.androidquest1.data.remote.db.PostsDatabase
import com.jeno.androidquest1.data.repository.PostsRepositoryImpl
import com.jeno.androidquest1.repository.PostsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    private const val TIMEOUT = 30L

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
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

    @Provides
    @Singleton
    fun providePostsRepository(
        postsRepositoryImpl: PostsRepositoryImpl
    ): PostsRepository {
        return postsRepositoryImpl
    }
}