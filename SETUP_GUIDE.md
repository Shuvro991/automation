# 🚀 AutoFlow AI - Complete Setup Guide

## Step 1: Create Android Project in Android Studio

### 1.1 Open Android Studio
- Launch Android Studio
- Click "New Project" or "Create New Project"

### 1.2 Project Configuration
```
Template: Empty Activity
Name: AutoFlow AI
Package name: com.yourname.autoflowai
Save location: Choose your preferred directory
Language: Kotlin
Minimum SDK: API 24 (Android 7.0 Nougat)
```

### 1.3 Click "Finish" and wait for project creation

## Step 2: Update Build Configuration

### 2.1 Open `app/build.gradle.kts`
Replace the dependencies section with:

```kotlin
dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation("androidx.compose.ui:ui:1.5.8")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.8")
    implementation("androidx.compose.material3:material3:1.1.2")
    implementation("androidx.navigation:navigation-compose:2.7.6")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    
    // Google Auth
    implementation("com.google.android.gms:play-services-auth:20.7.0")
    implementation("com.google.firebase:firebase-auth-ktx:22.3.0")
    implementation("com.google.firebase:firebase-bom:32.7.0")
    
    // ML Kit and TensorFlow Lite
    implementation("com.google.mlkit:text-recognition:16.0.0")
    implementation("org.tensorflow:tensorflow-lite:2.14.0")
    implementation("org.tensorflow:tensorflow-lite-gpu:2.14.0")
    implementation("org.tensorflow:tensorflow-lite-support:0.4.4")
    
    // Permissions
    implementation("com.google.accompanist:accompanist-permissions:0.32.0")
    
    // Networking
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    
    // Room Database
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    
    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    
    // Image loading
    implementation("io.coil-kt:coil-compose:2.5.0")
    
    // JSON
    implementation("com.google.code.gson:gson:2.10.1")
    
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.5.8")
    debugImplementation("androidx.compose.ui:ui-tooling:1.5.8")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.5.8")
}
```

### 2.2 Add to the top of `app/build.gradle.kts` (after plugins):
```kotlin
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.gms.google-services") // Add this line
}
```

### 2.3 Add to project-level `build.gradle.kts`:
```kotlin
plugins {
    id("com.google.gms.google-services") version "4.4.0" apply false
}
```

## Step 3: Update AndroidManifest.xml

### 3.1 Open `app/src/main/AndroidManifest.xml`
Add these permissions before the `<application>` tag:

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.CAMERA" />
<uses-permission android:name="android.permission.RECORD_AUDIO" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.VIBRATE" />
<uses-permission android:name="android.permission.ACCESS_NOTIFICATION_POLICY" />
<uses-permission android:name="android.permission.BIND_NOTIFICATION_LISTENER_SERVICE" />
```

## Step 4: Copy Source Files

### 4.1 Create Directory Structure
In your project, create these directories under `app/src/main/java/com/yourname/autoflowai/`:

```
├── ui/
│   ├── theme/
│   ├── screens/
│   └── components/
├── auth/
├── ml/
├── utils/
├── data/
│   └── models/
└── sensors/
```

### 4.2 Copy Files
Copy all files from the `/kotlin-source/` folder to their corresponding locations:

- `MainActivity.kt` → `app/src/main/java/com/yourname/autoflowai/MainActivity.kt`
- `ui/theme/` files → `app/src/main/java/com/yourname/autoflowai/ui/theme/`
- `auth/` files → `app/src/main/java/com/yourname/autoflowai/auth/`
- And so on...

## Step 5: Firebase Setup (Optional for Testing)

### 5.1 Create Firebase Project
1. Go to [Firebase Console](https://console.firebase.google.com/)
2. Click "Create a project"
3. Follow the setup wizard

### 5.2 Add Android App
1. Click "Add app" → Android
2. Enter package name: `com.yourname.autoflowai`
3. Download `google-services.json`
4. Place it in your `app/` directory

### 5.3 Enable Authentication
1. In Firebase Console → Authentication
2. Sign-in method → Google → Enable

## Step 6: Sync and Build

### 6.1 Sync Project
- Click "Sync Now" when prompted
- Or go to File → Sync Project with Gradle Files

### 6.2 Build Project
- Build → Make Project (Ctrl+F9)
- Fix any import errors by adding missing imports

## Step 7: Run the App

### 7.1 Connect Device or Start Emulator
- Physical device: Enable USB debugging
- Emulator: Create and start an AVD

### 7.2 Run
- Click the green "Run" button
- Or press Shift+F10

## 🎉 Expected Results

### First Launch:
1. **Beautiful login screen** with AutoFlow AI branding
2. **Permission requests** for camera, location, etc.
3. **Google sign-in button** (will work after Firebase setup)

### After Login (or skipping auth):
1. **Gorgeous home dashboard** with quick actions
2. **Working navigation** between screens
3. **AI model interface** with mock responses
4. **Workflow management** system

## 🔧 Troubleshooting

### Common Issues:

**Build Errors:**
- Make sure all dependencies are added correctly
- Sync project after changes
- Clean and rebuild if needed

**Import Errors:**
- Android Studio should auto-import most classes
- Manually add imports if needed

**Firebase Issues:**
- For testing, you can skip Firebase setup
- The app will work with mock authentication

**Permission Issues:**
- Grant permissions when prompted
- Check device settings if permissions are denied

## 🚀 Next Steps

1. **Test the app** - Navigate through all screens
2. **Try AI models** - Test the instruction and multimodal interfaces
3. **Create workflows** - Use the workflow creation feature
4. **Customize** - Update colors, branding, package names
5. **Add real models** - Replace mock AI with actual TensorFlow Lite models

The app is designed to work immediately with beautiful mock data and responses!