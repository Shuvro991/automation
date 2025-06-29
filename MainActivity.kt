package com.yourname.autoflowai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AutoFlowAITheme {
                AutoFlowApp()
            }
        }
    }
}

@Composable
fun AutoFlowApp() {
    var currentScreen by remember { mutableStateOf("home") }
    
    when (currentScreen) {
        "home" -> HomeScreen(
            onNavigateToModels = { currentScreen = "models" },
            onNavigateToWorkflows = { currentScreen = "workflows" }
        )
        "models" -> ModelScreen(onBack = { currentScreen = "home" })
        "workflows" -> WorkflowScreen(onBack = { currentScreen = "home" })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToModels: () -> Unit,
    onNavigateToWorkflows: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("⚡", fontSize = 24.sp, modifier = Modifier.padding(end = 8.dp))
                        Text("AutoFlow AI", fontWeight = FontWeight.Bold, color = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFF4A00)
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Welcome Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFF4A00).copy(alpha = 0.1f)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text(
                        text = "Welcome to AutoFlow AI! 🚀",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Your intelligent automation platform is ready",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
            
            // Action Buttons
            Button(
                onClick = onNavigateToModels,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0066CC)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("🤖", fontSize = 24.sp, modifier = Modifier.padding(end = 12.dp))
                Text("Try AI Models", fontSize = 18.sp, fontWeight = FontWeight.Medium, color = Color.White)
            }
            
            Button(
                onClick = onNavigateToWorkflows,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00CC66)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("⚡", fontSize = 24.sp, modifier = Modifier.padding(end = 12.dp))
                Text("Create Workflows", fontSize = 18.sp, fontWeight = FontWeight.Medium, color = Color.White)
            }
            
            // Features
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text("✨ Features:", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(12.dp))
                    FeatureItem("🤖", "On-device AI Models")
                    FeatureItem("⚡", "Smart Automation")
                    FeatureItem("🔒", "Privacy First")
                    FeatureItem("📱", "Sensor Integration")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModelScreen(onBack: () -> Unit) {
    var inputText by remember { mutableStateOf("") }
    var outputText by remember { mutableStateOf("") }
    var isProcessing by remember { mutableStateOf(false) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("🤖 AI Models", color = Color.White) },
                navigationIcon = {
                    TextButton(onClick = onBack) {
                        Text("← Back", color = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF0066CC))
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text("Try our AI models:", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            
            OutlinedTextField(
                value = inputText,
                onValueChange = { inputText = it },
                label = { Text("Enter your message") },
                placeholder = { Text("Ask me anything...") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )
            
            Button(
                onClick = {
                    isProcessing = true
                    // Simulate AI processing with delay
                    outputText = when {
                        inputText.contains("hello", ignoreCase = true) -> 
                            "🤖 Hello! I'm AutoFlow AI. I can help you with automation, text processing, and smart workflows!"
                        inputText.contains("weather", ignoreCase = true) -> 
                            "🌤️ I can help you create weather-based automation workflows! For example, I could notify you when it's going to rain."
                        inputText.contains("reminder", ignoreCase = true) -> 
                            "⏰ I'll help you set up smart reminders! I can create location-based, time-based, or context-aware reminders."
                        inputText.contains("photo", ignoreCase = true) -> 
                            "📸 I can organize your photos automatically using AI! I'll categorize them by content, people, and locations."
                        else -> 
                            "🤖 AI Response: I understand you said '$inputText'. I can help with automation, smart workflows, photo organization, reminders, and much more!"
                    }
                    isProcessing = false
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = inputText.isNotBlank() && !isProcessing,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0066CC))
            ) {
                if (isProcessing) {
                    CircularProgressIndicator(modifier = Modifier.size(20.dp), color = Color.White)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Processing...", color = Color.White)
                } else {
                    Text("🚀 Process with AI", color = Color.White)
                }
            }
            
            if (outputText.isNotEmpty()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF0066CC).copy(alpha = 0.1f)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = outputText,
                        modifier = Modifier.padding(16.dp),
                        fontSize = 16.sp
                    )
                }
            }
            
            // Example prompts
            Text("💡 Try these examples:", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            ExamplePrompt("Hello, what can you do?") { inputText = it }
            ExamplePrompt("Help me organize my photos") { inputText = it }
            ExamplePrompt("Create a weather reminder") { inputText = it }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkflowScreen(onBack: () -> Unit) {
    var showCreateDialog by remember { mutableStateOf(false) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("⚡ Workflows", color = Color.White) },
                navigationIcon = {
                    TextButton(onClick = onBack) {
                        Text("← Back", color = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF00CC66))
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Your Workflows:", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            
            WorkflowCard("Smart Photo Organizer", "Automatically categorize photos using AI", true)
            WorkflowCard("Voice Commands", "Process voice inputs and execute actions", false)
            WorkflowCard("Notification Filter", "AI-powered notification prioritization", true)
            WorkflowCard("Weather Automation", "Location-based weather alerts", false)
            
            Button(
                onClick = { showCreateDialog = true },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF4A00)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("+ Create New Workflow", color = Color.White, fontSize = 16.sp)
            }
        }
    }
    
    if (showCreateDialog) {
        AlertDialog(
            onDismissRequest = { showCreateDialog = false },
            title = { Text("🎉 Workflow Created!") },
            text = { Text("Your new workflow has been added to the list. You can configure it by tapping on it.") },
            confirmButton = {
                TextButton(onClick = { showCreateDialog = false }) {
                    Text("Got it!")
                }
            }
        )
    }
}

@Composable
fun WorkflowCard(title: String, description: String, isActive: Boolean) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(description, color = Color.Gray, fontSize = 14.sp, modifier = Modifier.padding(top = 4.dp))
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = if (isActive) "🟢 Active" else "⏸️ Paused",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = if (isActive) "Running" else "Stopped",
                    fontSize = 10.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun FeatureItem(icon: String, text: String) {
    Row(
        modifier = Modifier.padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(icon, fontSize = 20.sp, modifier = Modifier.padding(end = 12.dp))
        Text(text, fontSize = 16.sp)
    }
}

@Composable
fun ExamplePrompt(text: String, onClick: (String) -> Unit) {
    Card(
        onClick = { onClick(text) },
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = "💬 $text",
            modifier = Modifier.padding(12.dp),
            fontSize = 14.sp,
            color = Color(0xFF666666)
        )
    }
}

@Composable
fun AutoFlowAITheme(content: @Composable () -> Unit) {
    MaterialTheme(content = content)
}