package com.example.livros

import android.app.Application

class LivroApplication : Application() {
    val database by lazy { LivroDatabase .getDatabase( this) }
    val repository by lazy {
        LivroRepository( database.livroDao()) }
}