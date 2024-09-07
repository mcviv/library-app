package com.example.library_app.ui.screens.home

import android.renderscript.Float3
import androidx.annotation.FloatRange
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.library_app.R
import com.example.library_app.navigations.ROUTE_ABOUT
import com.example.library_app.navigations.ROUTE_ADD_BOOK
import com.example.library_app.navigations.ROUTE_ADMIN
import com.example.library_app.navigations.ROUTE_BORROWED_BOOKS
import com.example.library_app.navigations.ROUTE_BORROW_BOOK
import com.example.library_app.navigations.ROUTE_RETURN_BOOK
import com.example.library_app.navigations.ROUTE_SIGNOUT
import com.example.library_app.navigations.ROUTE_VIEW_BOOK


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController
){
    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(text = "LIBRARY",
                        color = Color.Blue,)
                },
                navigationIcon = {
                    TextButton(onClick = {
                        navController.navigate(ROUTE_VIEW_BOOK)
                    }) {
//                        Icon(
//                            imageVector = Icons.Filled.MenuBook,
//                            contentDescription = "Add"
//                        )
                        Text(text = "OUR BOOKS")
                    }
                },
                actions = {
                    TextButton(onClick = {
                        navController.navigate(ROUTE_ADMIN)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Add,

                            contentDescription = "Add"
                        )
                        Text(text = "NEW BOOK")
                    }
                    var moreMenu by remember {
                        mutableStateOf(false)
                    }
                    TextButton(
                        onClick = {
                            moreMenu =! moreMenu
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = "Menu"
                        )
                        Text(text = "MENU")
                    }
                    DropdownMenu(
                        expanded = moreMenu,
                        onDismissRequest = { moreMenu = false },
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(10.dp)
                    ) {
                        DropdownMenuItem(
                            leadingIcon = {
//                                Icon(
//                                    imageVector = Icons.Filled.Logout,
//                                    contentDescription
//                                    = "Logout"
//                                )
                            },
                            text = {
                                Text(text = "SIGN OUT")
                            },
                            onClick = {
                                navController.navigate(ROUTE_SIGNOUT)
                            }
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
                .fillMaxWidth()
        ) {
            Image(
            painter = painterResource(id = R.drawable.books),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier

        )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
//                contentAlignment = Alignment.Center
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
                            .padding(0.dp)
                    ) {
                        Card(
                            onClick = { navController.navigate(ROUTE_BORROW_BOOK) },

                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                                    colors = CardDefaults.cardColors(
                                    containerColor = Color.Black // Set the background color to black
                                    )


                        ) {

                            Text(
                                text = "BORROW A BOOK",
                                textAlign = TextAlign.Center,
                                color = Color.Blue,
                                fontFamily = FontFamily.Serif,
                                fontStyle = FontStyle.Italic,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(36.dp)

                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp)
                    ) {
                        Card(
                            onClick = { navController.navigate(ROUTE_VIEW_BOOK) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Black // Set the background color to black
                            )
                        ) {

                            Text(
                                text = "BOOKLIST",
                                textAlign = TextAlign.Center,
                                color = Color.Blue,
                                fontFamily = FontFamily.Serif,
                                fontStyle = FontStyle.Italic,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(36.dp)
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp)
                    ) {
                        Card(
                            onClick = { navController.navigate(ROUTE_BORROWED_BOOKS)},
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Black // Set the background color to black
                            )

                        ) {

                            Text(
                                text = "MANAGE BORROWED BOOKS",
                                textAlign = TextAlign.Center,
                                color = Color.Blue,
                                fontFamily = FontFamily.Serif,
                                fontStyle = FontStyle.Italic,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(36.dp)
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp)
                    ) {
                        Card(
                            onClick = { navController.navigate(ROUTE_RETURN_BOOK)},
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Black // Set the background color to black
                            )


                        ) {

                            Text(
                                text = "BUY BOOK",
                                textAlign = TextAlign.Center,
                                color = Color.Blue,
                                fontFamily = FontFamily.Serif,
                                fontStyle = FontStyle.Italic,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(36.dp)
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp)
                    ) {
                        Card(
                            onClick = { navController.navigate(ROUTE_ABOUT)},
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Black // Set the background color to black
                            )

                        ) {

                            Text(
                                text = "ABOUT LIBRARY",
                                textAlign = TextAlign.Center,
                                color = Color.Blue,
                                fontFamily = FontFamily.Serif,
                                fontStyle = FontStyle.Italic,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(36.dp)
                            )
                        }
                    }




                }


            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun Preview(){
    HomeScreen(navController = rememberNavController())
}