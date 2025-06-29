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
                        Text("AutoFlow AI", fontWeight = FontWeight.Bold)
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
                Text("Try AI Models", fontSize = 18.sp, fontWeight = FontWeight.Medium)
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
                Text("Create Workflows", fontSize = 18.sp, fontWeight = FontWeight.Medium)
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
                title = { Text("🤖 AI Models") },
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
                    // Simulate AI processing
                    outputText = "🤖 AI Response: I understand you said '$inputText'. This is a demo response showing how our AI models work!"
                    isProcessing = false
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = inputText.isNotBlank() && !isProcessing
            ) {
                if (isProcessing) {
                    CircularProgressIndicator(modifier = Modifier.size(20.dp), color = Color.White)
                } else {
                    Text("🚀 Process with AI")
                }
            }
            
            if (outputText.isNotEmpty()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF0066CC).copy(alpha = 0.1f))
                ) {
                    Text(
                        text = outputText,
                        modifier = Modifier.padding(16.dp),
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkflowScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("⚡ Workflows") },
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
            
            WorkflowCard("Smart Photo Organizer", "Automatically categorize photos", true)
            WorkflowCard("Voice Commands", "Process voice inputs", false)
            WorkflowCard("Notification Filter", "AI-powered filtering", true)
            
            Button(
                onClick = { /* Add workflow */ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF4A00))
            ) {
                Text("+ Create New Workflow")
            }
        }
    }
}

@Composable
fun WorkflowCard(title: String, description: String, isActive: Boolean) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(title, fontWeight = FontWeight.Bold)
                Text(description, color = Color.Gray, fontSize = 14.sp)
            }
            Text(
                text = if (isActive) "🟢 Active" else "⏸️ Paused",
                fontSize = 12.sp
            )
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
fun AutoFlowAITheme(content: @Composable () -> Unit) {
    MaterialTheme(content = content)
}