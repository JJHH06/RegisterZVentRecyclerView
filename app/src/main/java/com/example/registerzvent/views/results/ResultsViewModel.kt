package com.example.registerzvent.views.results

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * viewModel de results
 * Jose Hurtarte 19707
 */
class ResultsViewModel(peopleInvited : Int, peopleRegistered : Int , summaryInvited: String): ViewModel() {



    //los compos anteriores son implementados con databinding para poder ser usados con viewModel en el XML
    private val _invitedPeople = MutableLiveData<Int>()
    val invitedPeople: LiveData<Int> get() = _invitedPeople

    private val _registeredPeople = MutableLiveData<Int>()
    val registeredPeople: LiveData<Int> get() = _registeredPeople

    private val _invitedSummary = MutableLiveData<String>()
    val invitedSummary: LiveData<String> get() = _invitedSummary



    /**
     * Se inicializan las variables con los parametros, para asi poder se puestas en la vista
     */
    init {
        Log.i("ResultsViewModel", "invitados totales son :$peopleInvited")

        _invitedPeople.value = peopleInvited

        _registeredPeople.value = peopleRegistered

        _invitedSummary.value = summaryInvited

    }
}