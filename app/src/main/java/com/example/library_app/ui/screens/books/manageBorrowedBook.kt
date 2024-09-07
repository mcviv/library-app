package com.example.library_app.ui.screens.books


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.library_app.data.BookViewModel
import com.example.library_app.models.Book
import com.example.library_app.navigations.ROUTE_HOME
import com.example.library_app.navigations.ROUTE_UPDATE_BOOK


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageBorrowedBooks(
    navController: NavHostController
){
    val context = LocalContext.current
    val bookRepository = BookViewModel(
        navController, context
    )
    val emptyBookState = remember {
        mutableStateOf(
            Book(
                "",
                "",
                "",
                ""
            )
        )
    }
    val emptyBooksListState = remember {
        mutableStateListOf<Book>()
    }
    val books = bookRepository.viewBorrowedBooks(
        emptyBookState,
        emptyBooksListState
    )
    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = { Text(text = "Borrowed Books") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(ROUTE_HOME)
                    }) {
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
        ) {
//            Image(
//                painter = painterResource(id = R.drawable.pic5),
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
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.8f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                    ){
                        items(books){
                            BorrowedBookDetailCard(
                                navController,
                                bookRepository,
                                id = it.id,
                                isbn = it.isbn,
                                title = it.title,
                                author = it.author
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun BorrowedBookDetailCard(
    navController: NavHostController,
    bookRepository: BookViewModel,
    id: String,
    isbn: String,
    title: String,
    author: String
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = """
                ISBN: $isbn
                Title: $title
                Author: $author
            """.trimIndent(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ){
                TextButton(
                    onClick = {
                        bookRepository.ReturnBook(id)
                    }
                ) {
                    Text(
                        text = "RETURN"
                    )
                }

            }
        }
    }
}
