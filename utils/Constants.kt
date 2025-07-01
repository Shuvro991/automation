package com.yourname.autoflowai.utils

object Constants {
    // App Configuration
    const val APP_NAME = "AutoFlow AI"
    const val APP_VERSION = "1.0.0"
    
    // Database
    const val DATABASE_NAME = "autoflow_ai_db"
    const val DATABASE_VERSION = 1
    
    // Shared Preferences
    const val PREFS_NAME = "autoflow_ai_prefs"
    const val PREF_USER_LOGGED_IN = "user_logged_in"
    const val PREF_FIRST_LAUNCH = "first_launch"
    
    // Model Configuration
    const val MODEL_INSTRUCTION_NAME = "instruction_llm"
    const val MODEL_MULTIMODAL_NAME = "multimodal_llm"
    const val MODEL_ASSETS_PATH = "models/"
    
    // Sensor Configuration
    const val SENSOR_UPDATE_INTERVAL = 1000L // 1 second
    const val LOCATION_UPDATE_DISTANCE = 1f // 1 meter
    
    // Workflow Types
    const val TRIGGER_SENSOR = "sensor"
    const val TRIGGER_TIME = "time"
    const val TRIGGER_MANUAL = "manual"
    const val TRIGGER_LOCATION = "location"
    
    const val ACTION_NOTIFICATION = "notification"
    const val ACTION_AI_PROCESS = "ai_process"
    const val ACTION_AUTOMATION = "automation"
    const val ACTION_SENSOR_READ = "sensor_read"
    
    // API Configuration
    const val API_TIMEOUT = 30L // seconds
    const val MAX_RETRY_ATTEMPTS = 3
    
    // UI Configuration
    const val ANIMATION_DURATION = 300L
    const val DEBOUNCE_DELAY = 500L
}