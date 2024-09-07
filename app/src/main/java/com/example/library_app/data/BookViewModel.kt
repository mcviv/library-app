package com.example.library_app.data

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.library_app.models.Book
import com.example.library_app.navigations.ROUTE_BORROWED_BOOKS
import com.example.library_app.navigations.ROUTE_BORROW_BOOK
import com.example.library_app.navigations.ROUTE_VIEW_BOOK
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.contracts.Returns

class BookViewModel(var navController: NavHostController, var context: Context) {
    //    var authRepository: AuthViewModel
    var progress: ProgressDialog

    init {
//        authRepository = AuthViewModel(navController, context)
//        if (!authRepository.isSignedIn()) {
//            navController.navigate(ROUTE_SIGNIN)
//        }
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait a moment...")
    }


    fun saveBook(
        title: String,
        author: String,
        isbn: String
    ) {
        var id = System.currentTimeMillis().toString()
        var bookData = Book(title, author, isbn, id)
        var bookRef = FirebaseDatabase.getInstance().getReference()
            .child("Books/$id")
        if (isbn.isEmpty() || title.isEmpty() || author.isEmpty()){
            Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            return
        }else{
            progress.show()
            bookRef.setValue(bookData).addOnCompleteListener {
                progress.dismiss()
                if (it.isSuccessful) {
                    Toast.makeText(
                        context,
                        "Book added successfully!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        context,
                        "ERROR: ${it.exception!!.message}",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }
    fun viewBooks(
        book: MutableState<Book>,
        books: SnapshotStateList<Book>
    ): SnapshotStateList<Book> {
        var ref = FirebaseDatabase.getInstance().getReference().child("Books")

        ref.addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    books.clear()
                    for (snap in snapshot.children) {
                        val value = snap.getValue(Book::class.java)
                        book.value = value!!
                        books.add(value)
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(
                        context,
                        error.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        )
        return books
    }


    fun deleteBook(id: String) {
        var delRef = FirebaseDatabase.getInstance().getReference()
            .child("Books/$id")
        progress.show()
        delRef.removeValue().addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(
                    context,
                    "The book is deleted",
                    Toast.LENGTH_SHORT
                ).show()
                navController.navigate(ROUTE_VIEW_BOOK)
            } else {
                Toast.makeText(
                    context,
                    it.exception!!.message,
                    Toast.LENGTH_SHORT
                ).show()
                navController.navigate(ROUTE_VIEW_BOOK)
            }
        }
    }

    fun updateBook(
        title: String,
        author: String,
        isbn: String,
        id: String
    ) {
        val updateRef = FirebaseDatabase.getInstance().getReference()
            .child("Books/$id")
        progress.show()
        val updateData = Book(title, author, isbn, id)
        updateRef.setValue(updateData).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(
                    context,
                    "Update successful",
                    Toast.LENGTH_SHORT
                ).show()
                navController.navigate(ROUTE_VIEW_BOOK)
            } else {
                Toast.makeText(
                    context,
                    it.exception!!.message,
                    Toast.LENGTH_SHORT
                ).show()
                navController.navigate(ROUTE_VIEW_BOOK)
            }
        }

    }

    fun borrowBook(
        bookTitle: String,
        borrowedBooksRef: DatabaseReference,
        callback: (String) -> Unit) {
        borrowedBooksRef.orderByChild("title").equalTo(bookTitle).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (!snapshot.exists()) {
                    // Book is not borrowed, add it to the borrowed books
//                val book = Book(title = bookTitle, author = bookAuthor, isbn = bookISBN)
                    borrowedBooksRef.push().setValue(Book())
                    callback("Book borrowed successfully.")
                    navController.navigate(ROUTE_BORROWED_BOOKS)
                } else {
                    callback("Sorry, this book is not available for now. Already borrowed. Try a different one.")
                    navController.navigate(ROUTE_BORROW_BOOK)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                callback("Error: ${error.message}")
            }
        })
    }

    fun viewBorrowedBooks(
        book: MutableState<Book>,
        books: SnapshotStateList<Book>): SnapshotStateList<Book> {
        var ref = FirebaseDatabase.getInstance().getReference().child("BorrowedBooks")

        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progress.dismiss()
                books.clear()
                for (snap in snapshot.children) {
                    val value = snap.getValue(Book::class.java)
                    book.value = value!!
                    books.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return books
    }
    fun ReturnBook(id: String) {
        var delRef = FirebaseDatabase.getInstance().getReference()
            .child("Books/$id")
        progress.show()
        delRef.removeValue().addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(
                    context,
                    "The book is returned",
                    Toast.LENGTH_SHORT
                ).show()
//                navController.navigate(ROUTE_VIEW_BOOK)
            } else {
                Toast.makeText(
                    context,
                    it.exception!!.message,
                    Toast.LENGTH_SHORT
                ).show()
//                navController.navigate(ROUTE_VIEW_BOOK)
            }
        }
    }


}