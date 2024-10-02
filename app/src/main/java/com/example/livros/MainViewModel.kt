package com.example.livros

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(private val repository: LivroRepository) : ViewModel() {
    val allLivros: LiveData<List<Livro>> = repository.allLivros
    fun insert(livro: Livro) {
        viewModelScope.launch {
            repository.insert(livro)
        }
    }
    fun update(livro: Livro) {
        viewModelScope.launch {
            repository.update(livro)
        }
    }
    fun delete(livro: Livro) {
        viewModelScope.launch {
            repository.delete(livro)
        }
    }
}