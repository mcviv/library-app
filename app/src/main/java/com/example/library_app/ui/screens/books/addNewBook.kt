package com.example.library_app.ui.screens.books

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.library_app.data.BookViewModel
import com.example.library_app.navigations.ROUTE_VIEW_BOOK


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewBook(
    navController: NavHostController
){
    val context = LocalContext.current
    var isbn by remember {
        mutableStateOf("")
    }
    var title by remember {
        mutableStateOf("")
    }
    var author by remember {
        mutableStateOf("")
    }
    val bookRepository = BookViewModel(
        navController,
        context
    )

    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Text("Add New Book")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    TextButton(
                        onClick = {
                            bookRepository.saveBook(
                                title.trim(),
                                author.trim(),
                                isbn.trim()
                            )
                            navController.navigate(ROUTE_VIEW_BOOK)
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = "Save"
                        )
                        Text("Save")
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
        ) {
//            Image(
//                painter = painterResource(id = R.drawable.pic3),
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
                Card (
                    modifier = Modifier
                        .fillMaxWidth(0.8f),
                ){
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
                                value = isbn,
                                onValueChange = {isbn = it},
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                label = {Text("ISBN")},
                                placeholder = {Text("Enter ISBN")}
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        ) {
                            OutlinedTextField(
                                value = title,
                                onValueChange = { title = it },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                label = {Text("Title")},
                                placeholder = {Text("Enter Title")}
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        ) {
                            OutlinedTextField(
                                value = author,
                                onValueChange = { author = it },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                label = {Text("Author")},
                                placeholder = {Text("Enter Author")}
                            )
                        }
                    }
                }
            }
        }
    }
}

