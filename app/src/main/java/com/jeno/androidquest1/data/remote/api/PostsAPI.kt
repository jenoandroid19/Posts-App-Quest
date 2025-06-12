package com.jeno.androidquest1.data.remote.api

import com.jeno.androidquest1.data.remote.api.model.Posts
import retrofit2.Response
import retrofit2.http.GET

interface PostsAPI {
    @GET("posts")
    suspend fun getPosts(): Response<List<Posts>>
}