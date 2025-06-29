package com.yourname.autoflowai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yourname.autoflowai.auth.GoogleAuthManager
import com.yourname.autoflowai.ui.screens.HomeScreen
import com.yourname.autoflowai.ui.screens.LoginScreen
import com.yourname.autoflowai.ui.screens.ModelScreen
import com.yourname.autoflowai.ui.screens.WorkflowScreen
import com.yourname.autoflowai.ui.theme.AutoFlowAITheme
import com.yourname.autoflowai.utils.PermissionManager

class MainActivity : ComponentActivity() {
    private lateinit var authManager: GoogleAuthManager
    private lateinit var permissionManager: PermissionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        authManager = GoogleAuthManager(this)
        permissionManager = PermissionManager(this)
        
        setContent {
            AutoFlowAITheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AutoFlowAIApp(authManager, permissionManager)
                }
            }
        }
    }
}

@Composable
fun AutoFlowAIApp(
    authManager: GoogleAuthManager,
    permissionManager: PermissionManager
) {
    val navController = rememberNavController()
    var isLoggedIn by remember { mutableStateOf(false) }
    
    LaunchedEffect(Unit) {
        isLoggedIn = authManager.isUserLoggedIn()
        permissionManager.requestAllPermissions()
    }
    
    Scaffold { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = if (isLoggedIn) "home" else "login",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("login") {
                LoginScreen(
                    authManager = authManager,
                    onLoginSuccess = {
                        isLoggedIn = true
                        navController.navigate("home") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                )
            }
            
            composable("home") {
                HomeScreen(
                    navController = navController,
                    onLogout = {
                        authManager.signOut()
                        isLoggedIn = false
                        navController.navigate("login") {
                            popUpTo("home") { inclusive = true }
                        }
                    }
                )
            }
            
            composable("workflows") {
                WorkflowScreen(navController = navController)
            }
            
            composable("models") {
                ModelScreen(navController = navController)
            }
        }
    }
}