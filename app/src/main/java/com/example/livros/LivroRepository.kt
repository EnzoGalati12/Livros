package com.example.livros

import androidx.lifecycle.LiveData

class LivroRepository (private val livroDao : LivroDAO ) {
    val allLivros : LiveData<List<Livro>> =
        livroDao .getAllLivros()
    suspend fun insert(boardGame: Livro) {
        livroDao .insert(boardGame)
    }
    suspend fun update(boardGame: Livro) {
        livroDao .update(boardGame)
    }
    suspend fun delete(boardGame: Livro) {
        livroDao .delete(boardGame)
    }
}