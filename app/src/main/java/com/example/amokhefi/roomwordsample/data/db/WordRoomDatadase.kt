package com.example.amokhefi.roomwordsample.data.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.amokhefi.roomwordsample.data.db.dao.WordDao
import com.example.amokhefi.roomwordsample.data.db.entity.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(
    entities = [Word::class],
    version = 1,
    exportSchema = false
)
abstract class WordRoomDatadase : RoomDatabase() {
    abstract fun wordDao(): WordDao


    companion object {
        private var instance: WordRoomDatadase? = null
        private val LOCK = Any()
        fun getInstance(context: Context): WordRoomDatadase {
            if (instance == null) {
                synchronized(LOCK) {
                    if (instance == null) buildDataBase(context).also { instance = it } else instance
                }
            }
            return instance!!
        }

        private fun buildDataBase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                WordRoomDatadase::class.java, "word_database"
            ).addCallback(object : RoomDatabase.Callback() {
                override fun onOpen(db: SupportSQLiteDatabase) {
                    super.onOpen(db)
                    addWords(instance)
                }
            }).build()

        private fun addWords(instance: WordRoomDatadase?) {
            GlobalScope.launch(Dispatchers.IO) {
                instance?.wordDao()?.insert(Word("Hello"))
                instance?.wordDao()?.insert(Word("Word"))

            }
        }
    }
}