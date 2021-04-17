package com.edward.myapplication.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edward.myapplication.database.Records
import com.edward.myapplication.database.RecordsDatabase
import com.edward.myapplication.database.RecordsDatabaseDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
//comment
//new comment

class AddViewModel(
    val databaseDao: RecordsDatabaseDAO,
    application: Application
):AndroidViewModel(application){

    private var currentRecord = MutableLiveData<Records?>()


    private val records = databaseDao.getAllRecords()

    //suspend is part of coroutines, launches viewmodescope on different thread
    fun addEntry(record: Records){
        viewModelScope.launch(Dispatchers.IO) {
            databaseDao.insert(record)
        }
    }


}