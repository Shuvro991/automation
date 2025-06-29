# 📁 AutoFlow AI - Project Structure Guide

## Complete Directory Structure

After creating your Android project, you need to create this exact folder structure:

```
app/src/main/java/com/yourname/autoflowai/
├── MainActivity.kt
├── ui/
│   ├── theme/
│   │   ├── Color.kt
│   │   ├── Theme.kt
│   │   └── Type.kt
│   ├── screens/
│   │   ├── LoginScreen.kt
│   │   ├── HomeScreen.kt
│   │   ├── WorkflowScreen.kt
│   │   └── ModelScreen.kt
│   └── components/
│       ├── ModelCard.kt
│       └── WorkflowCard.kt
├── data/
│   ├── models/
│   │   ├── User.kt
│   │   ├── Workflow.kt
│   │   └── ModelResult.kt
│   ├── database/
│   │   ├── AppDatabase.kt
│   │   └── WorkflowDao.kt
│   └── repository/
│       └── WorkflowRepository.kt
├── ml/
│   ├── InstructionLLM.kt
│   ├── MultimodalLLM.kt
│   └── ModelManager.kt
├── auth/
│   └── GoogleAuthManager.kt
├── sensors/
│   └── SensorManager.kt
└── utils/
    ├── PermissionManager.kt
    └── Constants.kt
```

## Step-by-Step Folder Creation

### 1. Navigate to Your Package
In Android Studio Project panel:
- Expand: `app` → `src` → `main` → `java` → `com` → `yourname` → `autoflowai`

### 2. Create Main Folders
Right-click on `autoflowai` and create these packages:

1. **Right-click** `autoflowai` → **New** → **Package** → Type `ui` → **Enter**
2. **Right-click** `autoflowai` → **New** → **Package** → Type `data` → **Enter**  
3. **Right-click** `autoflowai` → **New** → **Package** → Type `ml` → **Enter**
4. **Right-click** `autoflowai` → **New** → **Package** → Type `auth` → **Enter**
5. **Right-click** `autoflowai` → **New** → **Package** → Type `sensors` → **Enter**
6. **Right-click** `autoflowai` → **New** → **Package** → Type `utils` → **Enter**

### 3. Create UI Subfolders
Right-click on the `ui` folder and create:

1. **Right-click** `ui` → **New** → **Package** → Type `theme` → **Enter**
2. **Right-click** `ui` → **New** → **Package** → Type `screens` → **Enter**
3. **Right-click** `ui` → **New** → **Package** → Type `components` → **Enter**

### 4. Create Data Subfolders
Right-click on the `data` folder and create:

1. **Right-click** `data` → **New** → **Package** → Type `models` → **Enter**
2. **Right-click** `data` → **New** → **Package** → Type `database` → **Enter**
3. **Right-click** `data` → **New** → **Package** → Type `repository` → **Enter**

## Quick Creation Method

You can also create nested folders in one step:

1. **Right-click** `autoflowai` → **New** → **Package**
2. Type: `ui.theme` (creates both folders at once)
3. Repeat for: `ui.screens`, `ui.components`, `data.models`, etc.

## After Creating Folders

### Add Kotlin Files:
1. **Right-click** on any folder (e.g., `theme`)
2. Select **New** → **Kotlin Class/File**
3. Choose **File** for `.kt` files
4. Enter filename (e.g., `Color`)
5. Copy content from the corresponding file in `/kotlin-source/`

## Files to Create in Each Folder:

### `/ui/theme/`
- Color.kt
- Theme.kt  
- Type.kt

### `/ui/screens/`
- LoginScreen.kt
- HomeScreen.kt
- WorkflowScreen.kt
- ModelScreen.kt

### `/ui/components/`
- ModelCard.kt
- WorkflowCard.kt

### `/auth/`
- GoogleAuthManager.kt

### `/ml/`
- InstructionLLM.kt
- MultimodalLLM.kt
- ModelManager.kt

### `/utils/`
- PermissionManager.kt
- Constants.kt

### `/data/models/`
- User.kt
- Workflow.kt
- ModelResult.kt

### Root (`/autoflowai/`):
- MainActivity.kt

## Visual Reference:

```
🗂️ Your Project Structure Should Look Like:
├── 📁 autoflowai
│   ├── 📄 MainActivity.kt
│   ├── 📁 ui
│   │   ├── 📁 theme (3 files)
│   │   ├── 📁 screens (4 files)  
│   │   └── 📁 components (2 files)
│   ├── 📁 data
│   │   ├── 📁 models (3 files)
│   │   ├── 📁 database (2 files)
│   │   └── 📁 repository (1 file)
│   ├── 📁 ml (3 files)
│   ├── 📁 auth (1 file)
│   ├── 📁 sensors (1 file)
│   └── 📁 utils (2 files)
```

## Important Notes:

1. **Use "Package" not "Directory"** when creating folders for Kotlin code
2. **Package names should be lowercase** (Android convention)
3. **Copy files from `/kotlin-source/`** folder to corresponding locations
4. **Update package declarations** in each file to match your package name
5. **Sync project** after adding all files

This structure provides clean separation of concerns and follows Android development best practices!