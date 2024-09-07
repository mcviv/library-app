package com.example.library_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.library_app.data.AuthViewModel
import com.example.library_app.navigations.AppNavHost
import com.example.library_app.navigations.ROUTE_HOME
import com.example.library_app.navigations.ROUTE_WELCOME
import com.example.library_app.ui.theme.Library_AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Library_AppTheme  {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    val context = LocalContext.current
                    val account = AuthViewModel(
                        navController = NavHostController(context),
                        context = context
                    )
                    AppNavHost(
                        startDestination = if (account.isSignedIn()){
                            ROUTE_HOME
                        } else {
                            ROUTE_WELCOME
                        }
                    )
                }
            }
        }
    }
}
