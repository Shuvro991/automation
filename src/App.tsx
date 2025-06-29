import React from 'react';
import { FileText, Folder, Code, Download, Copy, CheckCircle, ArrowRight, FolderPlus } from 'lucide-react';

function App() {
  const structureSteps = [
    {
      step: 1,
      title: "Create Folder Structure in Android Studio",
      description: "You need to create these folders (packages) in your Android Studio project",
      icon: "📁",
      color: "bg-blue-50 border-blue-200"
    },
    {
      step: 2,
      title: "Copy Kotlin Files to Matching Folders",
      description: "Each .kt file goes into its corresponding folder you created",
      icon: "📄",
      color: "bg-green-50 border-green-200"
    },
    {
      step: 3,
      title: "Build and Run Your App",
      description: "After copying all files, sync and run your Android project",
      icon: "🚀",
      color: "bg-orange-50 border-orange-200"
    }
  ];

  const folderMappings = [
    {
      folder: "ui/theme/",
      files: ["Color.kt", "Theme.kt", "Type.kt"],
      description: "App colors, themes, and typography",
      icon: "🎨"
    },
    {
      folder: "ui/screens/",
      files: ["LoginScreen.kt", "HomeScreen.kt", "WorkflowScreen.kt", "ModelScreen.kt"],
      description: "All app screens and pages",
      icon: "📱"
    },
    {
      folder: "ui/components/",
      files: ["ModelCard.kt", "WorkflowCard.kt"],
      description: "Reusable UI components",
      icon: "🧩"
    },
    {
      folder: "auth/",
      files: ["GoogleAuthManager.kt"],
      description: "Google authentication logic",
      icon: "🔐"
    },
    {
      folder: "ml/",
      files: ["InstructionLLM.kt", "MultimodalLLM.kt", "ModelManager.kt"],
      description: "AI model integration",
      icon: "🤖"
    },
    {
      folder: "utils/",
      files: ["PermissionManager.kt", "Constants.kt"],
      description: "Helper functions and constants",
      icon: "🛠️"
    }
  ];

  return (
    <div className="min-h-screen bg-gradient-to-br from-indigo-50 to-blue-100 p-6">
      <div className="max-w-6xl mx-auto">
        {/* Header */}
        <div className="text-center mb-8">
          <div className="inline-flex items-center justify-center w-16 h-16 bg-indigo-500 rounded-full mb-4">
            <Folder className="w-8 h-8 text-white" />
          </div>
          <h1 className="text-4xl font-bold text-gray-900 mb-2">
            📁 Project Structure Explained
          </h1>
          <p className="text-xl text-gray-600">
            How to organize your Android Studio project folders and files
          </p>
        </div>

        {/* What This Means */}
        <div className="bg-yellow-50 border-l-4 border-yellow-400 p-6 mb-8 rounded-r-lg">
          <div className="flex items-center">
            <CheckCircle className="w-6 h-6 text-yellow-400 mr-3" />
            <div>
              <h3 className="text-lg font-semibold text-yellow-800">What "Project Structure" Means</h3>
              <p className="text-yellow-700 mt-1">
                This shows you <strong>how to organize folders</strong> in your Android Studio project. 
                It's like a blueprint for where each file should go.
              </p>
            </div>
          </div>
        </div>

        {/* Process Steps */}
        <div className="grid md:grid-cols-3 gap-6 mb-8">
          {structureSteps.map((step, index) => (
            <div key={index} className={`${step.color} border-2 rounded-xl p-6`}>
              <div className="flex items-center mb-4">
                <div className="w-8 h-8 bg-white rounded-full flex items-center justify-center text-sm font-bold text-gray-700 mr-3">
                  {step.step}
                </div>
                <span className="text-2xl">{step.icon}</span>
              </div>
              <h3 className="font-bold text-gray-900 mb-2">{step.title}</h3>
              <p className="text-gray-700 text-sm">{step.description}</p>
            </div>
          ))}
        </div>

        {/* Visual Structure */}
        <div className="bg-white rounded-xl shadow-lg p-6 mb-8">
          <h2 className="text-2xl font-bold text-gray-900 mb-6 flex items-center">
            <Code className="w-6 h-6 mr-2 text-indigo-500" />
            Android Studio Folder Structure
          </h2>
          
          <div className="bg-gray-50 rounded-lg p-6 font-mono text-sm">
            <div className="text-gray-800 font-semibold mb-2">📁 app/src/main/java/com/yourname/autoflowai/</div>
            <div className="ml-4 space-y-1">
              <div className="text-gray-600">├── 📄 MainActivity.kt</div>
              <div className="text-gray-600">├── 📁 ui/</div>
              <div className="ml-4 space-y-1">
                <div className="text-gray-600">│   ├── 📁 theme/</div>
                <div className="ml-4 space-y-1">
                  <div className="text-gray-500">│   │   ├── 📄 Color.kt</div>
                  <div className="text-gray-500">│   │   ├── 📄 Theme.kt</div>
                  <div className="text-gray-500">│   │   └── 📄 Type.kt</div>
                </div>
                <div className="text-gray-600">│   ├── 📁 screens/</div>
                <div className="text-gray-600">│   └── 📁 components/</div>
              </div>
              <div className="text-gray-600">├── 📁 auth/</div>
              <div className="text-gray-600">├── 📁 ml/</div>
              <div className="text-gray-600">└── 📁 utils/</div>
            </div>
          </div>
        </div>

        {/* Folder Mappings */}
        <div className="bg-white rounded-xl shadow-lg p-6 mb-8">
          <h2 className="text-2xl font-bold text-gray-900 mb-6 flex items-center">
            <FolderPlus className="w-6 h-6 mr-2 text-green-500" />
            What Goes Where
          </h2>
          
          <div className="grid gap-4">
            {folderMappings.map((mapping, index) => (
              <div key={index} className="border border-gray-200 rounded-lg p-4 hover:bg-gray-50 transition-colors">
                <div className="flex items-start justify-between">
                  <div className="flex items-start space-x-4">
                    <div className="text-2xl">{mapping.icon}</div>
                    <div className="flex-1">
                      <h3 className="font-semibold text-gray-900 mb-1">
                        📁 {mapping.folder}
                      </h3>
                      <p className="text-sm text-gray-600 mb-2">{mapping.description}</p>
                      <div className="flex flex-wrap gap-2">
                        {mapping.files.map((file, fileIndex) => (
                          <span key={fileIndex} className="px-2 py-1 bg-blue-100 text-blue-800 text-xs rounded-md font-mono">
                            {file}
                          </span>
                        ))}
                      </div>
                    </div>
                  </div>
                  <ArrowRight className="w-5 h-5 text-gray-400 mt-1" />
                </div>
              </div>
            ))}
          </div>
        </div>

        {/* Step by Step Instructions */}
        <div className="bg-indigo-50 rounded-xl p-6 mb-8">
          <h3 className="text-xl font-bold text-indigo-900 mb-4">🎯 Step-by-Step Instructions:</h3>
          <div className="space-y-4 text-indigo-800">
            <div className="flex items-start space-x-3">
              <span className="bg-indigo-200 text-indigo-800 rounded-full w-6 h-6 flex items-center justify-center text-sm font-bold flex-shrink-0">1</span>
              <div>
                <p className="font-semibold">Create Folders in Android Studio</p>
                <p className="text-sm">Right-click your package → New → Package → Create: ui, auth, ml, utils, etc.</p>
              </div>
            </div>
            <div className="flex items-start space-x-3">
              <span className="bg-indigo-200 text-indigo-800 rounded-full w-6 h-6 flex items-center justify-center text-sm font-bold flex-shrink-0">2</span>
              <div>
                <p className="font-semibold">Copy Files from kotlin-source/</p>
                <p className="text-sm">Each .kt file in kotlin-source/ goes to its matching folder in Android Studio</p>
              </div>
            </div>
            <div className="flex items-start space-x-3">
              <span className="bg-indigo-200 text-indigo-800 rounded-full w-6 h-6 flex items-center justify-center text-sm font-bold flex-shrink-0">3</span>
              <div>
                <p className="font-semibold">Sync and Build</p>
                <p className="text-sm">Click "Sync Now" in Android Studio, then build and run your app</p>
              </div>
            </div>
          </div>
        </div>

        {/* Quick Reference */}
        <div className="grid md:grid-cols-2 gap-6">
          <div className="bg-white rounded-lg p-6 shadow-md">
            <h4 className="font-bold text-gray-900 mb-3 flex items-center">
              <FileText className="w-5 h-5 mr-2 text-blue-500" />
              Available Setup Guides:
            </h4>
            <ul className="space-y-2 text-sm text-gray-600">
              <li>• <code className="bg-gray-100 px-1 rounded">EASY_SETUP.md</code> - 5-minute quick setup</li>
              <li>• <code className="bg-gray-100 px-1 rounded">FOLDER_CREATION_GUIDE.md</code> - How to create folders</li>
              <li>• <code className="bg-gray-100 px-1 rounded">SETUP_GUIDE.md</code> - Complete detailed guide</li>
            </ul>
          </div>
          
          <div className="bg-white rounded-lg p-6 shadow-md">
            <h4 className="font-bold text-gray-900 mb-3 flex items-center">
              <CheckCircle className="w-5 h-5 mr-2 text-green-500" />
              What You'll Get:
            </h4>
            <ul className="space-y-2 text-sm text-gray-600">
              <li>• Beautiful Material Design 3 UI</li>
              <li>• Working AI model interfaces</li>
              <li>• Google authentication setup</li>
              <li>• Complete workflow automation system</li>
            </ul>
          </div>
        </div>

        {/* Final Note */}
        <div className="bg-green-50 border border-green-200 rounded-lg p-4 mt-8">
          <p className="text-green-800 text-center">
            <strong>Remember:</strong> The "Project Structure" is just a <strong>folder organization guide</strong>. 
            Create these folders in Android Studio, then copy the matching .kt files from kotlin-source/ into them!
          </p>
        </div>
      </div>
    </div>
  );
}

export default App;