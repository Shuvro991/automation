package com.yourname.zapierclone.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.yourname.zapierclone.ui.components.ModelCard
import com.yourname.zapierclone.ui.components.WorkflowCard
import com.yourname.zapierclone.ui.theme.ZapierBlue
import com.yourname.zapierclone.ui.theme.ZapierGreen
import com.yourname.zapierclone.ui.theme.ZapierOrange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    onLogout: () -> Unit
) {
    var showLogoutDialog by remember { mutableStateOf(false) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "⚡",
                            fontSize = 24.sp,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(
                            text = "AutoFlow AI",
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { showLogoutDialog = true }) {
                        Icon(Icons.Default.ExitToApp, contentDescription = "Logout")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("workflows") },
                containerColor = ZapierOrange
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Create Workflow",
                    tint = Color.White
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Welcome Section
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = ZapierOrange.copy(alpha = 0.1f)
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp)
                    ) {
                        Text(
                            text = "Welcome back! 👋",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Your AI-powered automation platform is ready",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }
            
            // Quick Actions
            item {
                Text(
                    text = "Quick Actions",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(quickActions) { action ->
                        QuickActionCard(
                            title = action.title,
                            icon = action.icon,
                            color = action.color,
                            onClick = {
                                when (action.route) {
                                    "models" -> navController.navigate("models")
                                    "workflows" -> navController.navigate("workflows")
                                }
                            }
                        )
                    }
                }
            }
            
            // AI Models Section
            item {
                Text(
                    text = "AI Models",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(aiModels) { model ->
                        ModelCard(
                            title = model.title,
                            description = model.description,
                            status = model.status,
                            onClick = { navController.navigate("models") }
                        )
                    }
                }
            }
            
            // Recent Workflows
            item {
                Text(
                    text = "Recent Workflows",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    recentWorkflows.forEach { workflow ->
                        WorkflowCard(
                            title = workflow.title,
                            description = workflow.description,
                            isActive = workflow.isActive,
                            lastRun = workflow.lastRun,
                            onClick = { navController.navigate("workflows") }
                        )
                    }
                }
            }
        }
    }
    
    // Logout Dialog
    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            title = { Text("Logout") },
            text = { Text("Are you sure you want to logout?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showLogoutDialog = false
                        onLogout()
                    }
                ) {
                    Text("Logout")
                }
            },
            dismissButton = {
                TextButton(onClick = { showLogoutDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
private fun QuickActionCard(
    title: String,
    icon: String,
    color: Color,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .width(140.dp)
            .height(100.dp),
        colors = CardDefaults.cardColors(
            containerColor = color.copy(alpha = 0.1f)
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = icon,
                fontSize = 32.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                color = color
            )
        }
    }
}

// Sample data
private data class QuickAction(
    val title: String,
    val icon: String,
    val color: Color,
    val route: String
)

private data class AIModel(
    val title: String,
    val description: String,
    val status: String
)

private data class RecentWorkflow(
    val title: String,
    val description: String,
    val isActive: Boolean,
    val lastRun: String
)

private val quickActions = listOf(
    QuickAction("AI Models", "🤖", ZapierBlue, "models"),
    QuickAction("Workflows", "⚡", ZapierOrange, "workflows"),
    QuickAction("Analytics", "📊", ZapierGreen, "analytics")
)

private val aiModels = listOf(
    AIModel("Instruction LLM", "Text processing and commands", "Ready"),
    AIModel("Multimodal LLM", "Image and text analysis", "Ready")
)

private val recentWorkflows = listOf(
    RecentWorkflow("Smart Notifications", "AI-powered notification filtering", true, "2 hours ago"),
    RecentWorkflow("Photo Organizer", "Automatic photo categorization", false, "1 day ago"),
    RecentWorkflow("Voice Commands", "Voice-to-action automation", true, "30 minutes ago")
)