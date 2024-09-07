package com.example.library_app.ui.screens.books

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.library_app.data.BookViewModel
import com.example.library_app.navigations.ROUTE_HOME

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReturnBorrowedBook(
    navController: NavHostController
){
    var context = LocalContext.current
    var bookState = BookViewModel(navController, context)
    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Text("Buy books via 0706314626",
                           color = Color.Blue)
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) {
            innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ){
//            Image(
//                painter = painterResource(id = R.drawable.pic4),
//                contentDescription = "",
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .blur(300.dp)
//            )
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    ReturnBooksForm { bookTitle, author, isbn, returnDate ->
                        // Handle the book return action
                        // For example, you can print the details to the log
                        println("Returning book: $bookTitle by $author (ISBN: $isbn), Return Date: $returnDate")
                    }
                }
            }
        }
    }
}


@SuppressLint("RememberReturnType")
@Composable
fun ReturnBooksForm(
    onReturnBook: (String, String, String,String) -> Unit
) {
    val context = LocalContext.current
    var navController = NavHostController(context)
    val bookTitleState = remember {
        mutableStateOf(
            TextFieldValue()
        )
    }
    val authorState = remember {
        mutableStateOf(
            TextFieldValue()
        )
    }
    val isbnState = remember {
        mutableStateOf(
            TextFieldValue()
        )
    }
    val returnDateState = remember {
        mutableStateOf(
            TextFieldValue()
        )
    }
    Card(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .fillMaxHeight(0.9f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                OutlinedTextField(
                    value = bookTitleState.value,
                    onValueChange = { bookTitleState.value = it },
                    label = { Text("Book Title") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                OutlinedTextField(
                    value = authorState.value,
                    onValueChange = { authorState.value = it },
                    label = { Text("Author") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                OutlinedTextField(
                    value = isbnState.value,
                    onValueChange = { isbnState.value = it },
                    label = { Text("ISBN") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                OutlinedTextField(
                    value = returnDateState.value,
                    onValueChange = { returnDateState.value = it },
                    label = { Text("Buy Date") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            onReturnBook(
                                bookTitleState.value.text,
                                authorState.value.text,
                                isbnState.value.text,
                                returnDateState.value.text
                            )
                        }
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val mContext = LocalContext.current
                OutlinedButton(onClick ={
                    val simToolKitLaunchIntent =
                        mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                    simToolKitLaunchIntent?.let { mContext.startActivity(it) }

                },
                    modifier = Modifier
                        .size(width = 380.dp, height = 70.dp)
                        .padding(start = 20.dp, end = 20.dp, top = 20.dp),
                    shape = CutCornerShape(5.dp),
                    border = BorderStroke(3.dp, Color.Blue)
                ) {
                    Text(text = "PAY VIA MPESA")

                }


            }
        }
    }}
