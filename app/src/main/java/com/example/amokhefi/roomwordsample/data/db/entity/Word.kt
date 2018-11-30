package com.example.amokhefi.roomwordsample.data.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "word_table")
data class Word(
    @PrimaryKey
    @ColumnInfo(name = "word")
    val text: String
)