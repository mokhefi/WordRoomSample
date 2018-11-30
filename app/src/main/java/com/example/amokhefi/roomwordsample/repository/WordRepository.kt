package com.example.amokhefi.roomwordsample.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.amokhefi.roomwordsample.data.db.WordRoomDatadase
import com.example.amokhefi.roomwordsample.data.db.entity.Word
import kotlinx.coroutines.*

class WordRepository(val application: Application) {

    var _allWords = MutableLiveData<List<Word>>()
    private val wordRoomDatabase = WordRoomDatadase.getInstance(application)
    private val wordDao = wordRoomDatabase.wordDao()

     val allWords : LiveData<List<Word>>
         get() = _allWords

    init {
      GlobalScope.launch(Dispatchers.IO) {
          getAllWords()
      }
    }

    private suspend fun getAllWords(): LiveData<List<Word>> {
        return withContext(Dispatchers.IO) {
           val data =  wordDao.getAll()
            _allWords.postValue(data)
            _allWords
        }
    }

    fun insert(word: Word) {
        GlobalScope.launch(Dispatchers.IO) {
            wordDao.insert(word)
            getAllWords()
        }
    }

    fun deleteAll() {
        GlobalScope.launch(Dispatchers.IO) {
            wordDao.deleteAll()
            getAllWords()
        }
    }

    suspend fun getCount() {
       val number: Int =  withContext(Dispatchers.IO){
           wordDao.getRowCount()
       }

        Log.i("CountNumber", "$number")
    }


}