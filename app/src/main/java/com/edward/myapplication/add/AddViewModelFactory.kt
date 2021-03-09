package com.edward.myapplication.add


import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.edward.myapplication.database.RecordsDatabaseDAO
import java.lang.IllegalArgumentException
//this class create viewmodel with datasource and application as argument
/**
 * Its good practice if view model factory creates reference to our database so that
 * when viewmodel is created database will already be there for us.

 */
class AddViewModelFactory(
    private val dataSource: RecordsDatabaseDAO,
    private val application: Application
): ViewModelProvider.Factory{


    @Suppress("unchecked cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AddViewModel::class.java)){
            return AddViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }

}