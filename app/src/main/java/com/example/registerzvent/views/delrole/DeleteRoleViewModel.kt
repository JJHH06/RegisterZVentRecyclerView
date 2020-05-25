package com.example.registerzvent.views.delrole

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.registerzvent.database.Guest
import com.example.registerzvent.database.GuestRoleDatabaseDao
import com.example.registerzvent.database.Roles
import kotlinx.coroutines.*

class DeleteRoleViewModel(
    val database: GuestRoleDatabaseDao,
    application: Application ,
    roleId: Long) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    val uiScope = CoroutineScope(Dispatchers.Main +viewModelJob)

    val _selectedRole = MutableLiveData<Roles>(Roles(nombre = "",description = "xd",rolesOrder = 1))
    val selectedRole: LiveData<Roles>
        get() = _selectedRole

    var IdRespaldo = 1L


    init {
        IdRespaldo = roleId
        uiScope.launch {
        //Aqui inicializamos el delete role
        _selectedRole.value = getSelectedRole(IdRespaldo)}

    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun deleteSelectedRole(){
        uiScope.launch {
            getSelectedRole(IdRespaldo)?.let { deleteRole(it) }
        }
    }


    private suspend fun getSelectedRole(id: Long): Roles? {
        return withContext(Dispatchers.IO){
            database.getRole(id)
        }
    }

    private suspend fun deleteRole(roles: Roles){
        withContext(Dispatchers.IO){
            database.deleteRole(roles)
        }
    }

}
