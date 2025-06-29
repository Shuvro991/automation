package com.yourname.autoflowai.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionManager(private val context: Context) {
    
    companion object {
        private const val PERMISSION_REQUEST_CODE = 1001
        
        val REQUIRED_PERMISSIONS = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.VIBRATE,
            Manifest.permission.ACCESS_NOTIFICATION_POLICY
        )
    }
    
    fun requestAllPermissions() {
        val missingPermissions = REQUIRED_PERMISSIONS.filter { permission ->
            ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED
        }
        
        if (missingPermissions.isNotEmpty() && context is Activity) {
            ActivityCompat.requestPermissions(
                context,
                missingPermissions.toTypedArray(),
                PERMISSION_REQUEST_CODE
            )
        }
    }
    
    fun hasPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
    }
    
    fun hasAllPermissions(): Boolean {
        return REQUIRED_PERMISSIONS.all { permission ->
            ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
        }
    }
    
    fun hasCameraPermission(): Boolean = hasPermission(Manifest.permission.CAMERA)
    fun hasAudioPermission(): Boolean = hasPermission(Manifest.permission.RECORD_AUDIO)
    fun hasLocationPermission(): Boolean = 
        hasPermission(Manifest.permission.ACCESS_FINE_LOCATION) || 
        hasPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
    fun hasStoragePermission(): Boolean = 
        hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ||
        hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
}