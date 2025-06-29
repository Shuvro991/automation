package com.yourname.zapierclone.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workflows")
data class Workflow(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val isActive: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val lastRun: Long? = null,
    val triggerType: String, // "sensor", "time", "manual", etc.
    val actionType: String, // "notification", "ai_process", "automation", etc.
    val configuration: String // JSON string with workflow configuration
)