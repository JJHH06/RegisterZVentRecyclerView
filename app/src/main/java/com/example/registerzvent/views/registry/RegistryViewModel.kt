package com.example.registerzvent.views.registry

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.registerzvent.database.Guest
import com.example.registerzvent.database.GuestRoleDatabaseDao
import com.example.registerzvent.database.GuestWithRole
import kotlinx.coroutines.*

/**
 * Clase de ViewModel para el registro
 * Jose Hurtarte 19707
 */
class RegistryViewModel(val database: GuestRoleDatabaseDao,
                        application: Application): AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main +viewModelJob)

    //La lista que va a contener a todos los invitados exitentes, al menos en este caso
     var guests_register :List<GuestWithRole> = listOf()



    private val _contador = MutableLiveData<Int>()
    val contador: LiveData<Int>
        get() = _contador

    private val _finish = MutableLiveData<Boolean>(false)
    val finish: LiveData<Boolean>
        get() = _finish

    private val _invitadoActual = MutableLiveData<GuestWithRole>()
    val invitadoActual: LiveData<GuestWithRole>
        get() = _invitadoActual


    private var registrados = 0

    private var mensaje = ""


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
        Log.i("RegistryViewModel", "RegistryViewModel destroyed!")
    }
    init {
        Log.i("RegistryViewModel", "RegistryViewModel created!")
        uiScope.launch {
            guests_register = getGuestListSUSPEND().toMutableList()
            if (guests_register.isEmpty()) {
                _finish.value = true
                _invitadoActual.value = GuestWithRole(Guest(), nombreRol = "", roleOrder = 1)

            } else {
                //_invitadoActual.value = actualRegistryGuest()
                _contador.value = 0
            }

        }

    }

    private fun hasAlreadyFinished(){
        if (contador.value!! >= guests_register.size){
            _finish.value = true
        }

    }


    /**
     * Devuelve el invitado actual del registro
     */
    private fun actualRegistryGuest() : GuestWithRole? {
        hasAlreadyFinished()
        if (guests_register.isNotEmpty() && guests_register.size !=contador.value) {
            return guests_register[_contador.value!!]
        }
        else{
            return GuestWithRole(Guest(), nombreRol = "", roleOrder = 1)
        }
    }

    fun titleOfActionBar() : String{

        if(guests_register.isNullOrEmpty()){
            return ""
        }
        else {
            return "Registrando" + " (" + (_contador.value!! + 1) + "/" + guests_register.size + ")"
        }
    }



    fun progressInList(){
        _invitadoActual.value = actualRegistryGuest()
    }

    /**
     * Esta es la primera que se debe de llamar para poder mandar la info
     * si no se llama esa, el contador de registrados va a estar en 0 y el mensaje vacio
     *
     */
    fun finalToResults() {

            mensaje =""
            var posicion = 0 //posicion de la lista en la que esta posicionado


            registrados = 0

            //Aqui hago el loop para que recorra toda la lista
            while (posicion < guests_register.size) {
                mensaje += ", " + guests_register[posicion].guest.name + ": " + guests_register[posicion].guest.registered //Agrega a el string de resumen

                //Si esta registrado va a sumar 1 al contador de registrados
                if (guests_register[posicion].guest.registered == "Si") {
                    registrados++;
                }
                //se suma una posicion
                posicion++
            }

        mensaje += "." //Se agrega punto final al mensaje
        mensaje = mensaje.removePrefix(",")


    }

    /**
     * mensaje final que ira en el toast de resultados
     *
     * pre: debe de haber sido llamada la funcion finalToResult() para que de el mensaje bien
     * post: devuelve el texto concatenado que se realiza en la funcion de finalToResult()
     */
    fun finalResultMessage(): String{
        return mensaje
    }



    /**
     * Numero final de registrados
     *
     * pre: debe de haber sido llamada la funcion finalToResult() para que de el dato verdadero
     * post: dara la cantidad de personas que tengan 'Si' en el campo de registrados
     */
    fun finalNumberOfRegisteredGuests(): Int{
        return registrados
    }

    /**
     * dara el numero total de personas que han sido invitadas
     *
     * post: da la cantidad de Guests que estan en el mutableList de guest_register
     */
    fun numberOfInvitedPeople(): Int{
        return guests_register.size
    }



    private suspend fun getGuestListSUSPEND(): List<GuestWithRole> {
        return withContext(Dispatchers.IO){
            database.getAllGuestsNormalList()

            return@withContext database.getAllGuestsNormalList()

        }
    }


    /**
     * agrega 1 a la posicion en la que se esta en la lista de guests
     */
    private fun nextGuest(){
        _contador.value = (_contador.value)?.plus(1)
    }


    fun confirmedRegisterGuest(){
        guests_register[_contador.value!!].guest.registered = "Si"
        updateGuest(guests_register[_contador.value!!].guest) //Con esto actualizo luego de cambiar el estado de registered
        nextGuest()
    }

    fun deniedRegisterGuest(){
        guests_register[_contador.value!!].guest.registered = "No"
        updateGuest(guests_register[_contador.value!!].guest)//Con esto actualizo luego de cambiar el estado de registered
        nextGuest()
    }


    //Aqui estan las 2 funciones que utilizo para actualizar el invitado segun su estado de registro
    private suspend fun updateGuestDatabase(guest: Guest){
        withContext(Dispatchers.IO){
            database.updateGuest(guest)
        }
    }

    private fun updateGuest(guest: Guest){
        uiScope.launch {
            updateGuestDatabase(guest)
        }
    }

    fun listadoNoExiste():Boolean{
        return guests_register.isNullOrEmpty()
    }

}