package com.example.livros

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Livro::class], version = 1)
abstract class LivroDatabase : RoomDatabase() {
    abstract fun livroDao(): LivroDAO
    companion object {
        @Volatile
        private var INSTANCE: LivroDatabase? = null
        fun getDatabase(context: Context): LivroDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LivroDatabase::class.java,
                    "livro_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}