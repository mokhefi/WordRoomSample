package com.example.amokhefi.roomwordsample.data.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.amokhefi.roomwordsample.data.db.entity.Word

@Dao
interface WordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(word: Word)

    @Query("DELETE FROM word_table")
    fun deleteAll()

    @Query("SELECT * from word_table ORDER BY word ASC")
    fun getAll(): List<Word>

    @Query("SELECT COUNT(word) FROM word_table")
    fun getRowCount():Int

    @Query("SELECT * FROM word_table WHERE word == :search")
    fun searchWord(search: String): String
}