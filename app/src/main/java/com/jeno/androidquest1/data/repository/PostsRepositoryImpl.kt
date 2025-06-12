package com.jeno.androidquest1.data.repository

import com.jeno.androidquest1.data.remote.dao.PostsDao
import com.jeno.androidquest1.repository.PostsRepository
import com.jeno.androidquest1.data.remote.api.PostsAPI
import com.jeno.androidquest1.data.remote.api.model.Posts
import com.jeno.androidquest1.data.remote.entity.PostEntity
import com.jeno.androidquest1.runSuspendCatching
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    private val postsAPI: PostsAPI,
    private val postsDao: PostsDao
): PostsRepository {
    override suspend fun fetchPostsAndStore(): Result<Unit> {
        return postsAPI.runSuspendCatching {
            getPosts()
        }.mapCatching { response ->
            if (response.isSuccessful) {
                val posts = response.body().orEmpty().map { it.mapToPostsEntity() }
                if (posts.isEmpty()) {
                    throw Exception("Posts response body is null or empty")
                } else {
                    upsertAllPosts(posts)
                }
            } else {
                throw Exception("fetch posts failed")
            }
        }
    }

    override fun getAllPosts(): Flow<List<PostEntity>> {
        return postsDao.getAllPosts()
    }


    override suspend fun getPostById(id: Int): Result<PostEntity> {
        return postsDao.getPostById(id)?.let {
            Result.success(it)
        } ?: Result.failure(Exception("No post with id:$id found!"))
    }

    override suspend fun upsertAllPosts(list: List<PostEntity>) {
        postsDao.upsertAllPosts(list)
    }

    private fun Posts.mapToPostsEntity(): PostEntity {
        return PostEntity(
            id = id,
            userId = userId,
            title = title,
            body = body
        )
    }
}