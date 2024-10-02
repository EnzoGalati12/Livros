package com.example.livros

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.livros.databinding.LivroItemBinding

class MainListAdapter(
    private val onEditClick: (Livro) -> Unit,
    private val onDeleteClick: (Livro) -> Unit
) : RecyclerView.Adapter<MainListAdapter.LivroViewHolder>() {
    private var livro: List<Livro> = emptyList()
    class LivroViewHolder(val binding: LivroItemBinding) :
        RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            LivroViewHolder {
        val binding =
            LivroItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LivroViewHolder(binding)
    }
    override fun onBindViewHolder(holder: LivroViewHolder, position: Int) {
        val currentLivro = livro[position]
        holder.binding.textViewLivroName.text = currentLivro.titulo
        holder.binding.textViewLivroAutor.text =
            currentLivro.autor

        Glide
            .with(holder.itemView.context)
            .load(currentLivro.livroUrl)
            .into(holder.binding.logoLivro)

        holder.binding.buttonEdit.setOnClickListener {
            onEditClick(currentLivro) }
        holder.binding.buttonDelete.setOnClickListener {
            onDeleteClick(currentLivro) }
    }
    override fun getItemCount() = livro.size
    fun setBoardGames(boardGames: List<Livro>) {
        this.livro = boardGames
        notifyDataSetChanged()
    }
}