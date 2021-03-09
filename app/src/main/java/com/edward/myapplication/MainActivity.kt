package com.edward.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.edward.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //add binding
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        //set the actionbar to use navigationUI class
        navController = this.findNavController(R.id.myNavHostFragment)

        //binding drawerlayout with application bar through navigation UI class
        drawerLayout = binding.drawerLayout
        NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout)

        //setting up navView with actionbar through NavigationUI{not sure tho}
        appBarConfiguration = AppBarConfiguration(navController.graph,drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)

        //this method lock the drawer in other destinations other than home
        lockDrawerInOtherDestinations()

    }

    private fun lockDrawerInOtherDestinations() {
        navController.addOnDestinationChangedListener{nc: NavController, nd : NavDestination,
        args: Bundle? ->
            if(nd.id == nc.graph.startDestination){
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            }
            else{
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }
    }

    //override onsupportnavigateup to control navigate back with up arrow
    override fun onSupportNavigateUp(): Boolean {
        //this line of code makes the menu item work in main activity
        return NavigationUI.navigateUp(navController,drawerLayout)
    }
}