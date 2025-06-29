package com.yourname.zapierclone.data.models

data class User(
    val id: String,
    val email: String,
    val displayName: String?,
    val photoUrl: String?,
    val createdAt: Long = System.currentTimeMillis()
)