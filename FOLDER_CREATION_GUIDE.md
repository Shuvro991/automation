# 📁 How to Create Folders in Android Studio

## Method 1: Using Right-Click Menu (Recommended)

### Step 1: Navigate to Your Package
1. In Android Studio, open the **Project** panel (left side)
2. Expand: `app` → `src` → `main` → `java` → `com` → `yourname` → `autoflowai`

### Step 2: Create Folders
1. **Right-click** on the `autoflowai` folder
2. Select **New** → **Package**
3. Type the folder name (e.g., `ui`)
4. Press **Enter**

### Step 3: Create Subfolders
1. **Right-click** on the newly created `ui` folder
2. Select **New** → **Package**
3. Type the subfolder name (e.g., `theme`)
4. Press **Enter**

## Method 2: Using File Menu

1. Select the parent folder in Project panel
2. Go to **File** → **New** → **Package**
3. Enter the package name
4. Press **Enter**

## Method 3: Create Multiple Levels at Once

You can create nested folders in one step:
1. Right-click on `autoflowai` folder
2. Select **New** → **Package**
3. Type: `ui.theme` (this creates both `ui` and `theme` folders)
4. Press **Enter**

## Complete Folder Structure to Create

Create these packages/folders under `com.yourname.autoflowai`:

```
📁 ui
  📁 theme
  📁 screens
  📁 components
📁 auth
📁 ml
📁 utils
📁 data
  📁 models
📁 sensors
```

## Step-by-Step Creation:

1. **Right-click** `autoflowai` → **New** → **Package** → Type `ui` → **Enter**
2. **Right-click** `ui` → **New** → **Package** → Type `theme` → **Enter**
3. **Right-click** `ui` → **New** → **Package** → Type `screens` → **Enter**
4. **Right-click** `ui` → **New** → **Package** → Type `components` → **Enter**
5. **Right-click** `autoflowai` → **New** → **Package** → Type `auth` → **Enter**
6. **Right-click** `autoflowai` → **New** → **Package** → Type `ml` → **Enter**
7. **Right-click** `autoflowai` → **New** → **Package** → Type `utils` → **Enter**
8. **Right-click** `autoflowai` → **New** → **Package** → Type `data` → **Enter**
9. **Right-click** `data` → **New** → **Package** → Type `models` → **Enter**
10. **Right-click** `autoflowai` → **New** → **Package** → Type `sensors` → **Enter**

## After Creating Folders:

### Add Kotlin Files:
1. **Right-click** on any folder (e.g., `theme`)
2. Select **New** → **Kotlin Class/File**
3. Choose **File** for `.kt` files
4. Enter filename (e.g., `Color`)
5. Copy-paste the content from the corresponding file in `/kotlin-source/`

## Visual Guide:

```
🗂️ Project Panel
├── 📁 app
│   ├── 📁 src
│   │   ├── 📁 main
│   │   │   ├── 📁 java
│   │   │   │   ├── 📁 com
│   │   │   │   │   ├── 📁 yourname
│   │   │   │   │   │   ├── 📁 autoflowai  ← Right-click here
│   │   │   │   │   │   │   ├── 📄 MainActivity.kt
│   │   │   │   │   │   │   ├── 📁 ui  ← Create this
│   │   │   │   │   │   │   ├── 📁 auth  ← Create this
│   │   │   │   │   │   │   └── 📁 ml  ← Create this
```

## Pro Tips:

1. **Package vs Directory**: In Android Studio, use "Package" instead of "Directory" for Java/Kotlin code
2. **Auto-completion**: Android Studio will suggest package names as you type
3. **Refactoring**: You can drag and drop files between packages later
4. **Naming**: Use lowercase for package names (Android convention)

## Quick Creation Method:

Instead of creating one by one, you can create multiple levels:
- Right-click `autoflowai`
- New → Package
- Type: `ui.theme.screens.components` (creates all at once)
- Repeat for other main packages

This creates the complete folder structure you need for the AutoFlow AI project!