package com.example.registerzvent.views.results

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

/**
 * ViewModelFactory de resultados
 *
 * Jose Hurtarte 19707
 */
class ResultsViewModelFactory(private val peopleInvited: Int, private val peopleRegistered:Int, private val invitedSummary:String) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultsViewModel::class.java)){
            return ResultsViewModel(peopleInvited, peopleRegistered, invitedSummary) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}