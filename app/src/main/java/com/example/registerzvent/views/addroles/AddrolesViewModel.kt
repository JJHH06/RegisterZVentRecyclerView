package com.example.registerzvent.views.addroles

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.registerzvent.database.GuestRoleDatabase
import com.example.registerzvent.database.GuestRoleDatabaseDao
import com.example.registerzvent.database.Roles
import kotlinx.coroutines.*

class AddrolesViewModel(
    val database: GuestRoleDatabaseDao,
    application: Application):AndroidViewModel(application){

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    private suspend fun insert(role: Roles){
        withContext(Dispatchers.IO){
            database.insertRole(role)
        }
    }


    fun addRole(name:String,description:String, order:Int){
        uiScope.launch {
            insert(Roles(nombre = name,description = description,rolesOrder = order))
        }
    }

    init{
        Log.i("AddRolesViewModel","created")
    }
    override fun onCleared(){
        super.onCleared()
        viewModelJob.cancel()
        Log.i("AddRolesViewModel","destroyed")
    }

}