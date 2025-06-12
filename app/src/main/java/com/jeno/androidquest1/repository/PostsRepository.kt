package com.jeno.androidquest1.repository

import com.jeno.androidquest1.data.remote.entity.PostEntity
import kotlinx.coroutines.flow.Flow

interface PostsRepository {

    suspend fun fetchPostsAndStore(): Result<Unit>

    fun getAllPosts(): Flow<List<PostEntity>>

    suspend fun getPostById(id: Int): Result<PostEntity>

    suspend fun upsertAllPosts(list: List<PostEntity>)
}