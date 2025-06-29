package com.yourname.zapierclone.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager as AndroidSensorManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SensorManager(private val context: Context) : SensorEventListener, LocationListener {
    
    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as AndroidSensorManager
    private val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    
    // Sensor data flows
    private val _accelerometerData = MutableStateFlow(FloatArray(3))
    val accelerometerData: StateFlow<FloatArray> = _accelerometerData
    
    private val _gyroscopeData = MutableStateFlow(FloatArray(3))
    val gyroscopeData: StateFlow<FloatArray> = _gyroscopeData
    
    private val _lightData = MutableStateFlow(0f)
    val lightData: StateFlow<Float> = _lightData
    
    private val _locationData = MutableStateFlow<Location?>(null)
    val locationData: StateFlow<Location?> = _locationData
    
    // Available sensors
    private val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    private val gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
    private val lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
    
    fun startSensorMonitoring() {
        // Register sensor listeners
        accelerometer?.let {
            sensorManager.registerListener(this, it, AndroidSensorManager.SENSOR_DELAY_NORMAL)
        }
        
        gyroscope?.let {
            sensorManager.registerListener(this, it, AndroidSensorManager.SENSOR_DELAY_NORMAL)
        }
        
        lightSensor?.let {
            sensorManager.registerListener(this, it, AndroidSensorManager.SENSOR_DELAY_NORMAL)
        }
        
        // Start location updates (with permission checks)
        try {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                1000L, // 1 second
                1f, // 1 meter
                this
            )
        } catch (e: SecurityException) {
            // Handle permission not granted
        }
    }
    
    fun stopSensorMonitoring() {
        sensorManager.unregisterListener(this)
        locationManager.removeUpdates(this)
    }
    
    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            when (it.sensor.type) {
                Sensor.TYPE_ACCELEROMETER -> {
                    _accelerometerData.value = it.values.clone()
                }
                Sensor.TYPE_GYROSCOPE -> {
                    _gyroscopeData.value = it.values.clone()
                }
                Sensor.TYPE_LIGHT -> {
                    _lightData.value = it.values[0]
                }
            }
        }
    }
    
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Handle accuracy changes if needed
    }
    
    override fun onLocationChanged(location: Location) {
        _locationData.value = location
    }
    
    fun getAvailableSensors(): List<String> {
        val sensors = mutableListOf<String>()
        
        if (accelerometer != null) sensors.add("Accelerometer")
        if (gyroscope != null) sensors.add("Gyroscope")
        if (lightSensor != null) sensors.add("Light Sensor")
        
        return sensors
    }
    
    fun isLocationEnabled(): Boolean {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }
}