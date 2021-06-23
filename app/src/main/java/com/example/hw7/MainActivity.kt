package com.example.hw7

//import okhttp3.*
import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.BufferedReader
import java.io.File

class MainActivity : AppCompatActivity() {
    lateinit var bottom_home_nav : BottomNavigationView
    lateinit var homeFragment: HomeFragment
    lateinit var sportsFragment: SportsFragment
    lateinit var techFragment: TechFragment
    lateinit var busiFragment: BusiFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        val apikey: String = getString(R.string.key)

        bottom_home_nav = findViewById(R.id.home_nav)

        homeFragment = HomeFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, homeFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        bottom_home_nav.setOnNavigationItemSelectedListener OnNavigationItemSelectedListener@{
            when (it.itemId) {
                R.id.home_item -> {
                    homeFragment = HomeFragment.newInstance(apikey)
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_layout, homeFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.sports_item -> {
                    sportsFragment = SportsFragment.newInstance(apikey)
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_layout, sportsFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.tech_item -> {
                    techFragment = TechFragment.newInstance(apikey)
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_layout, techFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.business_item -> {
                    busiFragment = BusiFragment.newInstance(apikey)
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_layout, busiFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
    }
}
