package com.edward.myapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//this is our room data base
//note: here entities means all the tables you want to add in this database
@Database(entities = [Records::class], version = 1, exportSchema = false)
abstract class RecordsDatabase : RoomDatabase(){
    //we are saying what dao to be used in this database
    abstract val recordsDatabaseDAO: RecordsDatabaseDAO
    //we use companion object because there is no need to instantiate this class
    //instead we get reference to our database inside the companion object.

    companion object{
        //volatile means value will always be uptodate
        @Volatile
        private var INSTANCE: RecordsDatabase? = null


        fun getInstance(context: Context): RecordsDatabase{
            synchronized(this){
            var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RecordsDatabase::class.java,
                        "records_history_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}