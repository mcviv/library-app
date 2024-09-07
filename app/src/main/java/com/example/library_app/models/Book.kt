package com.example.library_app.models

class Book {
    var title:String=""
    var author:String=""
    var isbn:String=""
    var id:String=""

    constructor(title:String,author:String,isbn:String,id:String){

        this.title = title
        this.author =author
        this.isbn=isbn
        this.id=id

    }
    constructor()
}
