# AutoFlow AI - Kotlin Android Project

## 🚀 Overview
A sophisticated automation platform with on-device LLMs, similar to Zapier, built for Android using Kotlin and Jetpack Compose.

## 📁 Project Structure

### `/android-setup/`
Contains the complete setup guide and project documentation.

### `/kotlin-source/`
Contains all the Kotlin source code files organized by feature:

- **MainActivity.kt** - Main application entry point
- **ui/theme/** - Material Design 3 theming
- **ui/screens/** - All application screens
- **ui/components/** - Reusable UI components
- **auth/** - Google authentication management
- **ml/** - AI model integration and management
- **utils/** - Utility classes and constants
- **data/models/** - Data models and entities
- **sensors/** - Sensor management and integration

## 🛠️ Setup Instructions

1. **Create New Android Project**
   - Open Android Studio
   - Create new project with Empty Activity
   - Use package name: `com.yourname.autoflowai`
   - Minimum SDK: API 24

2. **Copy Source Files**
   - Copy all files from `/kotlin-source/` to your project's `src/main/java/com/yourname/autoflowai/`
   - Follow the directory structure exactly as shown

3. **Update Dependencies**
   - Add all dependencies listed in `/android-setup/project-guide.md`
   - Sync your project

4. **Configure Firebase**
   - Set up Firebase project
   - Add `google-services.json` to your app directory
   - Enable Google Authentication

5. **Add Permissions**
   - Update AndroidManifest.xml with required permissions
   - Handle runtime permissions as needed

## 🎨 Features

- ✅ **Beautiful Material Design 3 UI**
- ✅ **Google Authentication**
- ✅ **On-device AI Models (M1 & M2)**
- ✅ **Workflow Automation**
- ✅ **Sensor Integration**
- ✅ **Real-time Processing**
- ✅ **Privacy-focused (On-device processing)**

## 🤖 AI Models

- **Instruction LLM (M1)** - Processes text commands and instructions
- **Multimodal LLM (M2)** - Handles image and text analysis together

## 📱 Screens

- **Login Screen** - Google authentication with feature highlights
- **Home Screen** - Dashboard with quick actions and workflows
- **Model Screen** - Interactive AI model testing interface
- **Workflow Screen** - Create and manage automation workflows

## 🔧 Next Steps

1. Replace placeholder values (Google Client ID, package names)
2. Add actual LLM model files to assets
3. Implement real TensorFlow Lite integration
4. Test on physical device for sensor functionality
5. Customize UI colors and branding as needed

The project provides a solid foundation for building a production-ready automation app with on-device AI capabilities.