package com.example.library_app.ui.screens.account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.library_app.data.AuthViewModel
import com.example.library_app.navigations.ROUTE_HOME
import com.example.library_app.navigations.ROUTE_SIGNIN


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp(
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        text = """
                                
                                SIGN IN
                            """.trimIndent(),
                        color = Color.Blue,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily.Serif,
                        fontStyle = FontStyle.Italic,
                        lineHeight = 64.sp,
                        fontSize = 40.sp,
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back to Previous Page"
                        )

                    }
                }
            )
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.8f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val context = LocalContext.current
                var account = AuthViewModel(navController, context)


                var email by remember {
                    mutableStateOf("")
                }
                var password by rememberSaveable {
                    mutableStateOf("")
                }
                var confirmPassword by rememberSaveable {
                    mutableStateOf("")
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {

                    OutlinedTextField(
                        value = email, onValueChange = {
                            email = it
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email
                        ),
                        placeholder = {
                            Text(text = "Enter Email")
                        },
                        label = {
                            Text(text = "Email")
                        },
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {

                    var passwordVisibility by remember {
                        mutableStateOf(false)
                    }

                    val icon = if (passwordVisibility)
                        Icons.Filled.ArrowBack
                    else
                        Icons.Filled.ArrowBack

                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                        },
                        placeholder = { Text(text = "Enter Password") },
                        label = { Text(text = "Password") },
                        trailingIcon = {
                            IconButton(onClick = {
                                passwordVisibility = !passwordVisibility
                            }) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = "Visibility Icon"
                                )
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        visualTransformation = if (passwordVisibility) VisualTransformation.None
                        else PasswordVisualTransformation(),
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {

                    var passwordVisibility by remember {
                        mutableStateOf(false)
                    }

                    val icon = if (passwordVisibility)
                        Icons.Filled.ArrowBack
                    else
                        Icons.Filled.ArrowBack

                    OutlinedTextField(
                        value = confirmPassword,
                        onValueChange = {
                            confirmPassword = it
                        },
                        placeholder = { Text(text = "Confirm Password") },
                        label = { Text(text = "Confirm Password") },
                        trailingIcon = {
                            IconButton(onClick = {
                                passwordVisibility = !passwordVisibility
                            }) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = "Visibility Icon"
                                )
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        visualTransformation = if (passwordVisibility) VisualTransformation.None
                        else PasswordVisualTransformation(),
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    Button(
                        onClick = {

                            account.signUp(
                                email.trim(),
                                password.trim()
                                )


                        },
                        modifier = Modifier
                            .padding(10.dp)
                    ) {
                        Text(text = "SIGN IN",
                            color = Color.Blue)
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Already have an account?")
                    ElevatedButton(
                        onClick = {
                            navController.navigate(ROUTE_SIGNIN)
                        },
                        modifier = Modifier
                            .padding(10.dp)
                    ) {
                        Text(text = "LOGIN")
                    }
                }


            }

        }
    }

}
