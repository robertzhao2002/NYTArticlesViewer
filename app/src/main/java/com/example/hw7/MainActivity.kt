package com.example.hw7

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.*
import java.io.IOException

//private val client = OkHttpClient()
//private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
//private val resultsJsonAdapter : JsonAdapter<Result> = moshi.adapter(Result::class.java)

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView : RecyclerView
    lateinit var mAdapter : RecyclerView.Adapter<*>
    lateinit var layoutManager : RecyclerView.LayoutManager
    lateinit var bottom_home_nav : BottomNavigationView
    lateinit var homeFragment: HomeFragment
    lateinit var sportsFragment: SportsFragment
    lateinit var techFragment: TechFragment
    lateinit var busiFragment: BusiFragment
    lateinit var requestGet: Request

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

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
                    homeFragment = HomeFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, homeFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.sports_item -> {
                    sportsFragment = SportsFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, sportsFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.tech_item -> {
                    techFragment = TechFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, techFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.business_item -> {
                    busiFragment = BusiFragment()
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
