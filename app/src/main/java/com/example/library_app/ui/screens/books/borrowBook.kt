package com.example.library_app.ui.screens.books

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.library_app.data.BookViewModel
import com.google.firebase.database.FirebaseDatabase


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BorrowBook(
    navController: NavHostController
){
    var context = LocalContext.current
    val bookTitleState = remember {
        mutableStateOf("")
    }
    val authorState = remember {
        mutableStateOf("")
    }
    val isbnState = remember {
        mutableStateOf("")
    }
    val borrowDateState = remember {
        mutableStateOf("")
    }
    val errorMessageState = remember {
        mutableStateOf<String?>(null)
    }
    val bookRepo = BookViewModel(navController, context)
    val borrowedBooksRef = FirebaseDatabase.getInstance()
        .getReference("BorrowedBooks")
    val bookTitle = "Book Title"
    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Text("Borrow Book")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    TextButton(
                        onClick = {
                            bookRepo.borrowBook(
                                bookTitle,
                                borrowedBooksRef
                            ) { message ->
                                Toast.makeText(context, message,
                                    Toast.LENGTH_SHORT).show()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = "Borrow Book"
                        )
                        Text("Save")
                    }
                }

            )
        },

        ) {
            innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
//            Image(
//                painter = painterResource(id = R.drawable.pic4),
//                contentDescription = "",
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .blur(300.dp)
//            )
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ){
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        ) {
                            OutlinedTextField(
                                value = isbnState.value,
                                onValueChange = {
                                    isbnState.value = it
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                label = {
                                    Text(text = "ISBN")
                                },
                                placeholder = {
                                    Text(text = "Enter ISBN")
                                }
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        ) {
                            OutlinedTextField(
                                value = bookTitleState.value,
                                onValueChange = {
                                    bookTitleState.value = it
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                label = {
                                    Text(text = "Title")
                                },
                                placeholder = {
                                    Text(text = "Enter Title")
                                }
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        ) {
                            OutlinedTextField(
                                value = authorState.value,
                                onValueChange = {
                                    authorState.value = it
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                label = {
                                    Text(text = "Author")
                                },
                                placeholder = {
                                    Text(text = "Enter Author")
                                }
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        ) {
                            OutlinedTextField(
                                value = borrowDateState.value,
                                onValueChange = {
                                    borrowDateState.value = it
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                label = {
                                    Text(text = "Borrow Date")
                                },
                                placeholder = {
                                    Text(text = "Enter Borrow Date")
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun BorrowBookPreview(){
    BorrowBook(navController = rememberNavController())
}