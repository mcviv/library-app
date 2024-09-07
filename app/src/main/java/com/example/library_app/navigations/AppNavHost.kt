package com.example.library_app.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.library_app.ui.screens.about.About
import com.example.library_app.ui.screens.account.Admin
import com.example.library_app.ui.screens.account.ForgotPassword
import com.example.library_app.ui.screens.account.SignIn
import com.example.library_app.ui.screens.account.SignOutAlert
import com.example.library_app.ui.screens.account.SignUp
import com.example.library_app.ui.screens.books.AddNewBook
import com.example.library_app.ui.screens.books.BorrowBook
import com.example.library_app.ui.screens.books.ManageBooks
import com.example.library_app.ui.screens.books.ManageBorrowedBooks
import com.example.library_app.ui.screens.books.ReturnBorrowedBook
import com.example.library_app.ui.screens.books.UpdateBook
import com.example.library_app.ui.screens.home.HomeScreen
import com.example.library_app.ui.screens.welcome.WelcomeScreen










@Composable
fun AppNavHost(

    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_WELCOME
){
    NavHost(

        navController = navController,
        startDestination = startDestination){

        composable(ROUTE_WELCOME){WelcomeScreen(navController)}
        composable(ROUTE_HOME){HomeScreen(navController)}
        composable(ROUTE_SIGNOUT){SignOutAlert(navController)}
        composable(ROUTE_SIGNIN){SignIn(navController)}
        composable(ROUTE_SIGNUP){SignUp(navController)}
        composable(ROUTE_FORGOT_PASSWORD){ForgotPassword(navController)}
        composable(ROUTE_ADD_BOOK){AddNewBook(navController)}
        composable(ROUTE_VIEW_BOOK){ManageBooks(navController)}
        composable(ROUTE_ABOUT){About(navController)}
        composable(ROUTE_ADMIN){Admin(navController)}
        composable(ROUTE_BORROW_BOOK){BorrowBook(navController) }
        composable(ROUTE_BORROWED_BOOKS){ManageBorrowedBooks(navController,)}
        composable(ROUTE_RETURN_BOOK){ReturnBorrowedBook(navController)}
        composable(ROUTE_UPDATE_BOOK + "/{id}"){passedData -> UpdateBook(navController,passedData.arguments?.getString("id")!!)}
    }
}
