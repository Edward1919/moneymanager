package com.edward.myapplication.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

//this is a Data access object interface, here we created all kotlin functions that will do the query and we
//linked it to the database queries
@Dao
interface RecordsDatabaseDAO {

    @Insert
    fun insert(record: Records)

    @Update
    fun update(record: Records)

    @Query("SELECT * FROM RECORDS_TABLE WHERE recordId = :key")
    fun get(key: Long) : Records?

    @Query("DELETE FROM records_table")
    fun clear()

    @Query("SELECT * FROM RECORDS_TABLE ORDER BY recordId DESC")
    fun getAllRecords() : LiveData<List<Records>>

    @Query("SELECT * FROM RECORDS_TABLE ORDER BY recordId DESC LIMIT 1")
    fun getCurrentRecord(): Records?

}