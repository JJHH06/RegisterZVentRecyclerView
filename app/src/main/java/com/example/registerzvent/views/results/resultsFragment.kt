package com.example.registerzvent.views.results

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.*
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.registerzvent.R
import com.example.registerzvent.databinding.FragmentResultsBinding
import com.example.registerzvent.views.results.*
import com.example.registerzvent.views.results.resultsFragmentDirections

//Jose Hurtarte 19707
/**
 * Clase del fragmento de resultados
 *
 */
class resultsFragment : Fragment() {
    private lateinit var viewModel: ResultsViewModel
    private lateinit var viewModelFactory: ResultsViewModelFactory

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentResultsBinding>(inflater,
            R.layout.fragment_results,container,false)


        //Aqui se pasan los argumentos que iran en resultados
        viewModelFactory = ResultsViewModelFactory(
            resultsFragmentArgs.fromBundle(arguments!!).invitedPeople,
            resultsFragmentArgs.fromBundle(arguments!!).registeredPeople,
            resultsFragmentArgs.fromBundle(arguments!!).invitedSummary)


        //Implementacion del viewModel con el factory
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ResultsViewModel::class.java)

        //Para el poner datos en el xml utilizando el ViewModel
        binding.resultsViewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner



        binding.buttonReset.setOnClickListener { view: View->
            view.findNavController()
                .navigate(resultsFragmentDirections.actionResultsFragmentToRegistryFragment()) //Navegacion en reinicio
        }

        //Aqui se muestra un toast con los invitados y su estado de registro
        binding.buttonSeeGuests.setOnClickListener {
            Toast.makeText(context, viewModel.invitedSummary.value, Toast.LENGTH_LONG).show()
        }



        //Se activa el menu para poder hacer el share
        setHasOptionsMenu(true)

        //se escribe resultados en actionbar
        (activity as AppCompatActivity).supportActionBar?.title = "Resultados"
        return binding.root
    }

    /*
    Devuelve un nuevo intento con los parametros a pasar
     */
    private fun getShareIntent(): Intent{

        val shareIntent = Intent(Intent.ACTION_SEND)
        //Tipo del parametro de salida del intento
            shareIntent.setType("text/plain")
                    //Coloca el parametro de salida de forma estructurada
                .putExtra(Intent.EXTRA_TEXT,"Estas personas estan registradas para el evento: \n"+viewModel.invitedSummary.value +
                        "\n\nResumen:\n"+"Invitados: " +viewModel.invitedPeople.value+"\nRegistrados: "+viewModel.registeredPeople.value)
        return shareIntent
    }

    /**
     * Comienza el intento que posee los parametros a salir, mandando a llamar
     * a Share intent para poder compartir
     */
    private fun shareRegistered(){
        startActivity(getShareIntent())
    }

    /**
     * Crea el menu de share, en donde esta contenido el boton de compartir
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.share_menu, menu)
        //Implementaciones para que se vea con animacion
        if (null == getShareIntent().resolveActivity(activity!!.packageManager)){

            menu?.findItem(R.id.share)?.setVisible(false)
        }
    }

    /**
     * Funciona como el click listener de el boton de share
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item!!.itemId){
            R.id.share -> shareRegistered() //Si se selecciona, manda a llamar el intento de salida para mandar datos

        }
        return super.onOptionsItemSelected(item)
    }
}
