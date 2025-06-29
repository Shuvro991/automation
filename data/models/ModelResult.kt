package com.yourname.zapierclone.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "model_results")
data class ModelResult(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val modelType: String, // "instruction" or "multimodal"
    val input: String,
    val output: String,
    val processingTime: Long, // in milliseconds
    val timestamp: Long = System.currentTimeMillis(),
    val confidence: Float? = null
)