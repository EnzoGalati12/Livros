package com.example.livros

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "boardgames")
data class Livro(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val titulo: String,
    val livroUrl: String,
    val autor: String
)