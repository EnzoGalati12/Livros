package com.example.livros

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface LivroDAO {
    @Insert
    suspend fun insert(livro: Livro)
    @Query("SELECT * FROM boardgames ORDER BY id ASC")
    fun getAllLivros(): LiveData<List<Livro>>
    @Update
    suspend fun update(livro: Livro)
    @Delete
    suspend fun delete(livro: Livro)
}