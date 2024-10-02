package com.example.livros

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.livros.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            (application as
                    LivroApplication).repository
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpLogo()
        setUpListeners()
        setUpRecyclerView()
    }

    private fun setUpListeners() {
        binding.buttonAddLivro.setOnClickListener {
            val livroTitulo = binding.editTextLivroName.text.toString()
            val urlImagem = binding.editTextLivroUrl.text.toString()
            val livroAutor =
                binding.editTextLivroAutor.text.toString()
            if (livroTitulo.isNotBlank() && livroAutor.isNotBlank()) {
                mainViewModel.insert(
                    Livro(
                        titulo = livroTitulo,
                        livroUrl = urlImagem,
                        autor = livroAutor
                    )
                )
                binding.editTextLivroName.text.clear()
                binding.editTextLivroUrl.text.clear()
                binding.editTextLivroAutor.text.clear()
                binding.editTextLivroName.requestFocus()
            }
        }
    }

    private fun showEditDialog(livro: Livro) {
        val dialogBinding = DialogEditLivroBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(this)
        builder.setView(dialogBinding.root)
        // Preenche os campos de texto com os dados do livro atual
        dialogBinding.editTextLivroName.setText(livro.titulo)
        dialogBinding.editTextLivroAutor.setText(livro.autor)
        Glide.with(this)
            .load(livro.livroUrl)
            .into(dialogBinding.logoLivro);

        builder.setTitle("Editar Livro")
        builder.setPositiveButton("Salvar") { _, _ ->
            val updatedLivro = livro.copy(
                titulo = dialogBinding.editTextLivroName.text.toString(),
                autor = dialogBinding.editTextLivroAutor.text.toString()
            )
            mainViewModel.update(updatedLivro)
        }
        builder.setNegativeButton("Cancelar", null)
        builder.show()
    }


    private fun setUpRecyclerView () {
        val adapter = MainListAdapter(
            onEditClick = { livro -> showEditDialog(livro) },
            onDeleteClick = { livro -> mainViewModel .delete(livro) }
        )
        binding.recyclerViewLivros .adapter = adapter
        binding.recyclerViewLivros .layoutManager = LinearLayoutManager( this)
        // Adicionar Divider
        val dividerItemDecoration = DividerItemDecoration(
            binding.recyclerViewLivros .context,
            (binding.recyclerViewLivros .layoutManager as
                    LinearLayoutManager ).orientation
        )
        binding.recyclerViewLivros .addItemDecoration( dividerItemDecoration )
        mainViewModel .allLivros .observe(this) { games ->
            games?.let { adapter.setBoardGames( it) }
        }
    }

    private fun setUpLogo() {
        Glide
            .with(this).load("https://static.vecteezy.com/system/resources/previews/006/404/900/original/board-game-logo-free-vector.jpg")
            .into(binding.imageLivro)
    }




}

