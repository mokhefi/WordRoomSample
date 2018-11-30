package com.example.amokhefi.roomwordsample.viewModel

import android.app.Application
import android.arch.lifecycle.ViewModel
import com.example.amokhefi.roomwordsample.data.db.entity.Word
import com.example.amokhefi.roomwordsample.repository.WordRepository

class WordViewModel(
    application: Application
) : ViewModel() {
    private val repository = WordRepository(application)
    val allWords = repository.allWords

    fun insert(word: Word) {
        repository.insert(word)
    }

    fun clearAll() {
        repository.deleteAll()
    }
}