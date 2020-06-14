package com.alien.brainean.coroutinesandflows.ui.posts

import com.alien.brainean.coroutinesandflows.data.apiService
import com.alien.brainean.coroutinesandflows.models.Post
import com.alien.brainean.coroutinesandflows.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PostsRepository {

    suspend fun getPosts1(): Result<List<Post>> {
        return try {
            val data = apiService.getPosts()
            return Result.Success(data)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    fun getPostsFlow(): Flow<List<Post>> {
        return flow {
            emit(apiService.getPosts())
        }.flowOn(Dispatchers.IO)
    }
}