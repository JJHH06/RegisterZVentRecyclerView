package com.example.registerzvent.views.activity

import android.content.ComponentCallbacks2
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.registerzvent.R
import com.example.registerzvent.databinding.ActivityMainBinding
import com.example.registerzvent.database.Guest

/**
 * Main Activity
 * Jose Hurtarte 19707
 */
class MainActivity : AppCompatActivity() {
    /*
     * en si esta clase define la estructura
     * para que se pueda utilizar el navigation
     */

    //binding para la Main activity
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout


    //Para el navigation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )
        drawerLayout = binding.drawerLayout

        //Como se utiliza un navigation se coloca esto, para que se controle la vista
        //desde el navigation
        val navController = this.findNavController(R.id.myNavHostFragment)

        NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }




}
