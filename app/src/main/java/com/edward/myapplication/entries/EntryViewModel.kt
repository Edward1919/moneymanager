package com.edward.myapplication.entries

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.edward.myapplication.database.RecordsDatabaseDAO

class EntryViewModel(
    val dataSource: RecordsDatabaseDAO,
    application: Application
):AndroidViewModel(application){


    val records = dataSource.getAllRecords()



}