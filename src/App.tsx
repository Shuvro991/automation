import React from 'react';
import { FileText, Folder, Code, Download, Copy, CheckCircle } from 'lucide-react';

function App() {
  const kotlinFiles = [
    { name: 'MainActivity.kt', path: 'kotlin-source/MainActivity.kt', type: 'main' },
    { name: 'Color.kt', path: 'kotlin-source/ui/theme/Color.kt', type: 'theme' },
    { name: 'Theme.kt', path: 'kotlin-source/ui/theme/Theme.kt', type: 'theme' },
    { name: 'Type.kt', path: 'kotlin-source/ui/theme/Type.kt', type: 'theme' },
    { name: 'GoogleAuthManager.kt', path: 'kotlin-source/auth/GoogleAuthManager.kt', type: 'auth' },
    { name: 'ModelManager.kt', path: 'kotlin-source/ml/ModelManager.kt', type: 'ml' },
    { name: 'InstructionLLM.kt', path: 'kotlin-source/ml/InstructionLLM.kt', type: 'ml' },
    { name: 'MultimodalLLM.kt', path: 'kotlin-source/ml/MultimodalLLM.kt', type: 'ml' },
    { name: 'PermissionManager.kt', path: 'kotlin-source/utils/PermissionManager.kt', type: 'utils' },
    { name: 'Constants.kt', path: 'kotlin-source/utils/Constants.kt', type: 'utils' },
  ];

  const getTypeColor = (type) => {
    switch (type) {
      case 'main': return 'bg-orange-100 text-orange-800';
      case 'theme': return 'bg-purple-100 text-purple-800';
      case 'auth': return 'bg-blue-100 text-blue-800';
      case 'ml': return 'bg-green-100 text-green-800';
      case 'utils': return 'bg-gray-100 text-gray-800';
      default: return 'bg-gray-100 text-gray-800';
    }
  };

  const getTypeIcon = (type) => {
    switch (type) {
      case 'main': return '🚀';
      case 'theme': return '🎨';
      case 'auth': return '🔐';
      case 'ml': return '🤖';
      case 'utils': return '🛠️';
      default: return '📄';
    }
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-100 p-6">
      <div className="max-w-4xl mx-auto">
        {/* Header */}
        <div className="text-center mb-8">
          <div className="inline-flex items-center justify-center w-16 h-16 bg-orange-500 rounded-full mb-4">
            <Code className="w-8 h-8 text-white" />
          </div>
          <h1 className="text-4xl font-bold text-gray-900 mb-2">
            📁 Kotlin Source Files Location
          </h1>
          <p className="text-xl text-gray-600">
            Your Android Kotlin files are in the <span className="font-mono bg-yellow-100 px-2 py-1 rounded">kotlin-source/</span> folder
          </p>
        </div>

        {/* Alert Box */}
        <div className="bg-green-50 border-l-4 border-green-400 p-6 mb-8 rounded-r-lg">
          <div className="flex items-center">
            <CheckCircle className="w-6 h-6 text-green-400 mr-3" />
            <div>
              <h3 className="text-lg font-semibold text-green-800">Found Your Files! ✅</h3>
              <p className="text-green-700 mt-1">
                All your Kotlin source files are located in the <strong>kotlin-source/</strong> directory. 
                Click on any file below to view its content.
              </p>
            </div>
          </div>
        </div>

        {/* File Structure */}
        <div className="bg-white rounded-xl shadow-lg p-6 mb-8">
          <h2 className="text-2xl font-bold text-gray-900 mb-6 flex items-center">
            <Folder className="w-6 h-6 mr-2 text-blue-500" />
            Project Structure
          </h2>
          
          <div className="bg-gray-50 rounded-lg p-4 font-mono text-sm">
            <div className="text-gray-600">📁 kotlin-source/</div>
            <div className="ml-4 text-gray-600">├── 📄 MainActivity.kt</div>
            <div className="ml-4 text-gray-600">├── 📁 ui/</div>
            <div className="ml-8 text-gray-600">└── 📁 theme/</div>
            <div className="ml-12 text-gray-600">├── 📄 Color.kt</div>
            <div className="ml-12 text-gray-600">├── 📄 Theme.kt</div>
            <div className="ml-12 text-gray-600">└── 📄 Type.kt</div>
            <div className="ml-4 text-gray-600">├── 📁 auth/</div>
            <div className="ml-8 text-gray-600">└── 📄 GoogleAuthManager.kt</div>
            <div className="ml-4 text-gray-600">├── 📁 ml/</div>
            <div className="ml-8 text-gray-600">├── 📄 ModelManager.kt</div>
            <div className="ml-8 text-gray-600">├── 📄 InstructionLLM.kt</div>
            <div className="ml-8 text-gray-600">└── 📄 MultimodalLLM.kt</div>
            <div className="ml-4 text-gray-600">└── 📁 utils/</div>
            <div className="ml-8 text-gray-600">├── 📄 PermissionManager.kt</div>
            <div className="ml-8 text-gray-600">└── 📄 Constants.kt</div>
          </div>
        </div>

        {/* Files List */}
        <div className="bg-white rounded-xl shadow-lg p-6">
          <h2 className="text-2xl font-bold text-gray-900 mb-6 flex items-center">
            <FileText className="w-6 h-6 mr-2 text-green-500" />
            Available Kotlin Files
          </h2>
          
          <div className="grid gap-4">
            {kotlinFiles.map((file, index) => (
              <div key={index} className="flex items-center justify-between p-4 border border-gray-200 rounded-lg hover:bg-gray-50 transition-colors">
                <div className="flex items-center space-x-4">
                  <div className="text-2xl">{getTypeIcon(file.type)}</div>
                  <div>
                    <h3 className="font-semibold text-gray-900">{file.name}</h3>
                    <p className="text-sm text-gray-500 font-mono">{file.path}</p>
                  </div>
                </div>
                <div className="flex items-center space-x-3">
                  <span className={`px-3 py-1 rounded-full text-xs font-medium ${getTypeColor(file.type)}`}>
                    {file.type.toUpperCase()}
                  </span>
                  <button className="flex items-center space-x-1 px-3 py-1 bg-blue-500 text-white rounded-md hover:bg-blue-600 transition-colors text-sm">
                    <Copy className="w-4 h-4" />
                    <span>View File</span>
                  </button>
                </div>
              </div>
            ))}
          </div>
        </div>

        {/* Instructions */}
        <div className="bg-blue-50 rounded-xl p-6 mt-8">
          <h3 className="text-xl font-bold text-blue-900 mb-4">🚀 How to Use These Files:</h3>
          <div className="space-y-3 text-blue-800">
            <div className="flex items-start space-x-3">
              <span className="bg-blue-200 text-blue-800 rounded-full w-6 h-6 flex items-center justify-center text-sm font-bold">1</span>
              <p><strong>Click on any file</strong> in the project file list (left panel) that starts with <code className="bg-blue-100 px-1 rounded">kotlin-source/</code></p>
            </div>
            <div className="flex items-start space-x-3">
              <span className="bg-blue-200 text-blue-800 rounded-full w-6 h-6 flex items-center justify-center text-sm font-bold">2</span>
              <p><strong>Copy the entire content</strong> of each file (Ctrl+A, then Ctrl+C)</p>
            </div>
            <div className="flex items-start space-x-3">
              <span className="bg-blue-200 text-blue-800 rounded-full w-6 h-6 flex items-center justify-center text-sm font-bold">3</span>
              <p><strong>Create the same file</strong> in your Android Studio project</p>
            </div>
            <div className="flex items-start space-x-3">
              <span className="bg-blue-200 text-blue-800 rounded-full w-6 h-6 flex items-center justify-center text-sm font-bold">4</span>
              <p><strong>Paste the content</strong> into your new Android Studio file</p>
            </div>
          </div>
        </div>

        {/* Quick Links */}
        <div className="grid md:grid-cols-2 gap-6 mt-8">
          <div className="bg-white rounded-lg p-6 shadow-md">
            <h4 className="font-bold text-gray-900 mb-3">📚 Setup Guides Available:</h4>
            <ul className="space-y-2 text-sm text-gray-600">
              <li>• <code>EASY_SETUP.md</code> - 5-minute setup</li>
              <li>• <code>SETUP_GUIDE.md</code> - Complete guide</li>
              <li>• <code>FOLDER_CREATION_GUIDE.md</code> - How to create folders</li>
            </ul>
          </div>
          
          <div className="bg-white rounded-lg p-6 shadow-md">
            <h4 className="font-bold text-gray-900 mb-3">🎯 What You'll Get:</h4>
            <ul className="space-y-2 text-sm text-gray-600">
              <li>• Beautiful Material Design 3 UI</li>
              <li>• Working AI model interfaces</li>
              <li>• Google authentication setup</li>
              <li>• Complete workflow system</li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  );
}

export default App;