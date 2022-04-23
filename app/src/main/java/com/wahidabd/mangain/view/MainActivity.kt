package com.wahidabd.mangain.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.wahidabd.mangain.R
import com.wahidabd.mangain.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHost = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        bottomNav = binding.bottomNavigationView
        navController = navHost.navController
        bottomNav.setupWithNavController(navController)

        navController.addOnDestinationChangedListener{_, dest, _ ->
            when(dest.id){
                R.id.homeFragment,
                    R.id.mangaFragment,
                    R.id.bookmarkFragment,
                    R.id.accountFragment
                    -> bottomNav.visibility = View.VISIBLE
                else -> bottomNav.visibility = View.GONE
            }
        }
    }
}