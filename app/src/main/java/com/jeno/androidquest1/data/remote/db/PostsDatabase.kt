package com.jeno.androidquest1.data.remote.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jeno.androidquest1.data.remote.dao.PostsDao
import com.jeno.androidquest1.data.remote.entity.PostEntity

@Database(
    entities = [PostEntity::class],
    version = 1
)


abstract class PostsDatabase: RoomDatabase() {
    abstract val postsDao: PostsDao
}