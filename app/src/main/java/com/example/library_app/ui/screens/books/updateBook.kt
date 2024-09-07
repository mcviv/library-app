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
import com.example.library_app.models.Book
import com.example.library_app.navigations.ROUTE_VIEW_BOOK
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateBook(
    navController: NavHostController,
    id: String
){
    var context = LocalContext.current
    var title by remember {
        mutableStateOf("")
    }
    var author by remember {
        mutableStateOf("")
    }
    var isbn by remember {
        mutableStateOf("")
    }
    var bookRepository = BookViewModel(
        navController,
        context
    )
    var currentDataRef = FirebaseDatabase.getInstance().getReference()
        .child("Books/$id")
    currentDataRef.addValueEventListener(object: ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            var book = snapshot.getValue(Book::class.java)
            title = book!!.title
            author = book!!.author
            isbn = book!!.isbn
        }

        override fun onCancelled(error: DatabaseError) {
            Toast.makeText(
                context,
                error.message,
                Toast.LENGTH_SHORT
            ).show()
        }
    })

    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Text("Update Book Details")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    TextButton(
                        onClick = {
                            bookRepository.updateBook(
                                title.trim(),
                                author.trim(),
                                isbn.trim(),
                                id
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
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
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
                                value = title,
                                onValueChange = { title = it },
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
                                value = author,
                                onValueChange = { author = it },
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
                    }
                }
            }
        }
    }
}
