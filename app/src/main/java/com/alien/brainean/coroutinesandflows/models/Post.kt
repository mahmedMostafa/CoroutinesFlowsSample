package com.alien.brainean.coroutinesandflows.models

data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)