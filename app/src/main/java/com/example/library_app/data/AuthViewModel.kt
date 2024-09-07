package com.example.library_app.data

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.example.library_app.models.User
import com.example.library_app.navigations.ROUTE_ADD_BOOK
import com.example.library_app.navigations.ROUTE_HOME
import com.example.library_app.navigations.ROUTE_SIGNIN
import com.example.library_app.navigations.ROUTE_SIGNUP
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AuthViewModel(var navController: NavController, var context: Context) {
    var mAuth: FirebaseAuth
    val progress: ProgressDialog

    init {
        mAuth = FirebaseAuth.getInstance()
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("PLease Wait a moment.....")
    }


    fun signUp(email: String,password: String){
        if (email.isBlank() || password.isBlank()){
            Toast.makeText(context,"Please fill all the fields",
                Toast.LENGTH_LONG).show()
            return
        }else{
            mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener{
                    if (it.isSuccessful){
                        val userdata= User(email,password,mAuth.currentUser!!.uid)
                        val regRef = FirebaseDatabase.getInstance().getReference()
                            .child("Users/"+mAuth.currentUser!!.uid)
                        regRef.setValue(userdata).addOnCompleteListener{
                            if (it.isSuccessful){
                                Toast.makeText(context,"Successfully Registered",
                                    Toast.LENGTH_LONG).show()
                                navController.navigate(ROUTE_HOME)



                            }else{
                                Toast.makeText(context,"${it.exception!!.message}",
                                    Toast.LENGTH_LONG).show()
                            }
                        }

                    }else
                    {
                        navController.navigate(ROUTE_SIGNIN)
                    }
                }
        }
    }

    fun signIn(email: String, password: String, ) {
        if (email.isNotBlank() && password.isNotBlank()){
            progress.show()
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
                progress.dismiss()
                if (it.isSuccessful){
                    Toast.makeText(context,"Successfully Logged in",Toast.LENGTH_LONG).show()
                    navController.navigate(ROUTE_HOME)
//                navController.navigate(ROUTE_REGISTER)TO TAKE YOU TO A DIFFERENT PAGE
                }else{
                    Toast.makeText(context,"${it.exception!!.message}",Toast.LENGTH_LONG).show()
                    navController.navigate(ROUTE_SIGNIN)
                }
            }
        }
        else{
            Toast.makeText(context, "Please fill in all the fields!", Toast.LENGTH_LONG).show()
        }
    }
    fun signOut(){
        mAuth.signOut()
        navController.navigate(ROUTE_SIGNIN)
    }

    fun isSignedIn():Boolean{
        return mAuth.currentUser !=null
    }

    fun resetPassword(email: String){
        if (email.isBlank()) {
            Toast.makeText(context,"Please enter your email",Toast.LENGTH_LONG).show()
            return
        }else{
            mAuth.sendPasswordResetEmail(email).addOnCompleteListener {
                if (it.isSuccessful) {
                    // Password reset email sent successfully
                    Toast.makeText(context,"Password reset email sent successfully",Toast.LENGTH_LONG).show()
                    navController.navigate(ROUTE_SIGNIN)
                } else {
                    // Password reset email could not be sent
                    Toast.makeText(context,"Password reset email could not be sent",Toast.LENGTH_LONG).show()
                }
            }
        }
//
    }
    fun Admin(email: String){
        if (email.isBlank()) {
            Toast.makeText(context,"Please enter your email",Toast.LENGTH_LONG).show()
            return
        }else{
            mAuth.sendPasswordResetEmail(email).addOnCompleteListener {
                if (it.isSuccessful) {
                    // Password reset email sent successfully
                    Toast.makeText(context,"Confirmed logged in as Admin",Toast.LENGTH_LONG).show()
                    navController.navigate(ROUTE_ADD_BOOK)
                } else {
                    // Password reset email could not be sent
                    Toast.makeText(context,"Confirmed error as occurred",Toast.LENGTH_LONG).show()
                }
            }
        }
//
    }
}