package com.example.library_app.ui.screens.account

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ExitToApp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.library_app.data.AuthViewModel
import com.example.library_app.navigations.ROUTE_WELCOME


@Composable
fun SignOutAlert(
    navController: NavHostController
){
    AlertDialog(
        onDismissRequest = {
            navController.navigateUp()
        },
        dismissButton = {
            TextButton(onClick = {
                navController.navigateUp()
            }) {
                Text("CANCEL")
            }
        },
        confirmButton = {
            val context = LocalContext.current
            val account = AuthViewModel(
                navController,
                context
            )
            TextButton(onClick = {
                account.signOut()
                navController.navigate(ROUTE_WELCOME)
            }) {
                Text(text = "SIGN OUT")
            }
        },
        icon = {
            Icon(
                imageVector = Icons.Sharp.ExitToApp,
                contentDescription = "Logout"
            )
        },
        title = {
            Text(text = "Sign Out")
        },
        text = {
            Text(text = "Are you sure you want to sign out?")
        },


        )
}