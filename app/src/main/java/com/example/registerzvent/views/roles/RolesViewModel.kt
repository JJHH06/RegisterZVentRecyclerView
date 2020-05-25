package com.example.registerzvent.views.roles

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.registerzvent.database.GuestRoleDatabaseDao
import com.example.registerzvent.database.Roles
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class RolesViewModel(
    val database: GuestRoleDatabaseDao,
    application: Application ): AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main +viewModelJob)

    val listaDeRoles = database.getAllRoles()
    val listaDeRolesString = Transformations.map(listaDeRoles){
        formatRoles(it)
    }

    override fun onCleared() {
        super.onCleared()

        Log.i("RolesViewModel","Destroyed")
        viewModelJob.cancel()
    }

    fun formatRoles(listado: List<Roles>): String{
        var resultado = ""

        if (listado.isEmpty()){
            resultado = "No hay roles en el programa"
        }
        else{

            for(role in listado){
                resultado += role.formatRoles()+"\n\n\n"
            }
        }
        return resultado
    }

}