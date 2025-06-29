package com.yourname.zapierclone.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.yourname.zapierclone.ui.components.WorkflowCard
import com.yourname.zapierclone.ui.theme.ZapierBlue
import com.yourname.zapierclone.ui.theme.ZapierGreen
import com.yourname.zapierclone.ui.theme.ZapierOrange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkflowScreen(navController: NavController) {
    var showCreateDialog by remember { mutableStateOf(false) }
    var workflows by remember { mutableStateOf(sampleWorkflows) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Workflows") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showCreateDialog = true },
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = ZapierBlue.copy(alpha = 0.1f)
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Text(
                            text = "⚡ Smart Workflows",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Automate tasks with AI-powered workflows",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }
            
            // Workflow Categories
            item {
                Text(
                    text = "Categories",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    CategoryChip("All", true) { }
                    CategoryChip("Active", false) { }
                    CategoryChip("Paused", false) { }
                }
            }
            
            // Workflows List
            item {
                Text(
                    text = "Your Workflows",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            
            items(workflows) { workflow ->
                WorkflowCard(
                    title = workflow.title,
                    description = workflow.description,
                    isActive = workflow.isActive,
                    lastRun = workflow.lastRun,
                    onClick = { /* Handle workflow click */ }
                )
            }
            
            // Empty state or add more workflows prompt
            if (workflows.isEmpty()) {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f)
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "🤖",
                                fontSize = 48.sp,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )
                            Text(
                                text = "No workflows yet",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Create your first AI-powered workflow to get started",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
    
    // Create Workflow Dialog
    if (showCreateDialog) {
        CreateWorkflowDialog(
            onDismiss = { showCreateDialog = false },
            onCreateWorkflow = { title, description ->
                val newWorkflow = WorkflowData(
                    id = workflows.size + 1,
                    title = title,
                    description = description,
                    isActive = false,
                    lastRun = "Never"
                )
                workflows = workflows + newWorkflow
                showCreateDialog = false
            }
        )
    }
}

@Composable
private fun CategoryChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    FilterChip(
        onClick = onClick,
        label = { Text(text) },
        selected = isSelected,
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = ZapierOrange,
            selectedLabelColor = Color.White
        )
    )
}

@Composable
private fun CreateWorkflowDialog(
    onDismiss: () -> Unit,
    onCreateWorkflow: (String, String) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Create New Workflow") },
        text = {
            Column {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Workflow Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (title.isNotBlank() && description.isNotBlank()) {
                        onCreateWorkflow(title, description)
                    }
                },
                enabled = title.isNotBlank() && description.isNotBlank()
            ) {
                Text("Create")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

// Sample data
private data class WorkflowData(
    val id: Int,
    val title: String,
    val description: String,
    val isActive: Boolean,
    val lastRun: String
)

private val sampleWorkflows = listOf(
    WorkflowData(
        id = 1,
        title = "Smart Photo Organizer",
        description = "Automatically categorize and tag photos using AI",
        isActive = true,
        lastRun = "2 hours ago"
    ),
    WorkflowData(
        id = 2,
        title = "Voice Command Handler",
        description = "Process voice commands and execute actions",
        isActive = true,
        lastRun = "30 minutes ago"
    ),
    WorkflowData(
        id = 3,
        title = "Notification Filter",
        description = "AI-powered notification prioritization",
        isActive = false,
        lastRun = "1 day ago"
    ),
    WorkflowData(
        id = 4,
        title = "Document Scanner",
        description = "Extract and process text from images",
        isActive = true,
        lastRun = "5 minutes ago"
    )
)