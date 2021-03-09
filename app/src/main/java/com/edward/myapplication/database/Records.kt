package com.edward.myapplication.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//this is our data class, it tells what columns and types of data we will have in our data base


@Entity(tableName = "records_table")
data class Records(
    @PrimaryKey(autoGenerate = true)
    var recordId: Long = 0L,

    @ColumnInfo(name = "name")
    var recordName: String ,
    @ColumnInfo(name = "amount")
    var recordAmount: String ,

    @ColumnInfo(name = "category")
    var recordCategory: Boolean = false,

    @ColumnInfo(name = "date")
    var recordDate: Long = 0L,

    @ColumnInfo(name = "time")
    var recordTime: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "note")
    var recordNote: String


)