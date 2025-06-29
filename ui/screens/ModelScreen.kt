package com.yourname.zapierclone.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.yourname.zapierclone.ml.ModelManager
import com.yourname.zapierclone.ui.theme.ZapierBlue
import com.yourname.zapierclone.ui.theme.ZapierGreen
import com.yourname.zapierclone.ui.theme.ZapierOrange
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModelScreen(navController: NavController) {
    var selectedModel by remember { mutableStateOf("instruction") }
    var inputText by remember { mutableStateOf("") }
    var outputText by remember { mutableStateOf("") }
    var isProcessing by remember { mutableStateOf(false) }
    
    val scope = rememberCoroutineScope()
    val modelManager = remember { ModelManager() }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("AI Models") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Model Selection
            item {
                Text(
                    text = "Select AI Model",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    ModelSelectionChip(
                        text = "Instruction LLM",
                        isSelected = selectedModel == "instruction",
                        onClick = { selectedModel = "instruction" }
                    )
                    ModelSelectionChip(
                        text = "Multimodal LLM",
                        isSelected = selectedModel == "multimodal",
                        onClick = { selectedModel = "multimodal" }
                    )
                }
            }
            
            // Model Info Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = if (selectedModel == "instruction") 
                            ZapierBlue.copy(alpha = 0.1f) 
                        else 
                            ZapierGreen.copy(alpha = 0.1f)
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = if (selectedModel == "instruction") "🤖" else "👁️",
                                fontSize = 24.sp,
                                modifier = Modifier.padding(end = 12.dp)
                            )
                            Column {
                                Text(
                                    text = if (selectedModel == "instruction") 
                                        "Instruction Following LLM (M1)" 
                                    else 
                                        "Multimodal LLM (M2)",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = if (selectedModel == "instruction")
                                        "Processes text instructions and commands"
                                    else
                                        "Analyzes images and text together",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                                )
                            }
                        }
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            StatusChip("🟢 Ready", ZapierGreen)
                            StatusChip("⚡ On-device", ZapierOrange)
                            StatusChip("🔒 Private", ZapierBlue)
                        }
                    }
                }
            }
            
            // Input Section
            item {
                Text(
                    text = "Input",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                OutlinedTextField(
                    value = inputText,
                    onValueChange = { inputText = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { 
                        Text(
                            if (selectedModel == "instruction")
                                "Enter your instruction or command..."
                            else
                                "Describe what you want to analyze..."
                        )
                    },
                    minLines = 4,
                    maxLines = 8,
                    shape = RoundedCornerShape(12.dp)
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Button(
                    onClick = {
                        scope.launch {
                            isProcessing = true
                            outputText = ""
                            try {
                                val result = if (selectedModel == "instruction") {
                                    modelManager.processInstruction(inputText)
                                } else {
                                    modelManager.processMultimodal(inputText)
                                }
                                outputText = result
                            } catch (e: Exception) {
                                outputText = "Error: ${e.message}"
                            } finally {
                                isProcessing = false
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = inputText.isNotBlank() && !isProcessing,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedModel == "instruction") ZapierBlue else ZapierGreen
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    if (isProcessing) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Processing...")
                    } else {
                        Icon(
                            Icons.Default.Send,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Process with AI")
                    }
                }
            }
            
            // Output Section
            if (outputText.isNotEmpty() || isProcessing) {
                item {
                    Text(
                        text = "Output",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        if (isProcessing) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(32.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    CircularProgressIndicator(
                                        color = if (selectedModel == "instruction") ZapierBlue else ZapierGreen
                                    )
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Text(
                                        text = "AI is processing your request...",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                                    )
                                }
                            }
                        } else {
                            Text(
                                text = outputText,
                                modifier = Modifier.padding(16.dp),
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
            }
            
            // Usage Examples
            item {
                Text(
                    text = "Example Prompts",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                val examples = if (selectedModel == "instruction") {
                    listOf(
                        "Create a reminder for tomorrow at 9 AM",
                        "Summarize the key points from this text",
                        "Generate a creative story about space exploration",
                        "Help me write a professional email"
                    )
                } else {
                    listOf(
                        "Analyze this image and describe what you see",
                        "Extract text from this document image",
                        "Identify objects in this photo",
                        "Compare these two images and highlight differences"
                    )
                }
                
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    examples.forEach { example ->
                        ExampleCard(
                            text = example,
                            onClick = { inputText = example }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ModelSelectionChip(
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
private fun StatusChip(text: String, color: Color) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = color.copy(alpha = 0.1f)
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            style = MaterialTheme.typography.bodySmall,
            color = color,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun ExampleCard(
    text: String,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f)
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
        )
    }
}