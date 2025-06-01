package com.example.a36_study_android_xml.feature.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.a36_study_android_xml.R
import com.example.a36_study_android_xml.databinding.ActivityMainBinding
import com.example.a36_study_android_xml.feature.history.HistoryFragment
import com.example.a36_study_android_xml.feature.home.HomeFragment
import com.example.a36_study_android_xml.feature.live.LiveFragment
import com.example.a36_study_android_xml.feature.my.MyFragment
import com.example.a36_study_android_xml.feature.search.SearchFragment
import com.example.a36_study_android_xml.feature.shorts.ShortsFragment
import com.example.a36_study_android_xml.feature.signin.SignInFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportFragmentManager.addOnBackStackChangedListener {
            val currentFragment = supportFragmentManager.findFragmentById(R.id.main_fcv)
            currentFragment?.let { updateBottomNavVisibility(it) }
        }

        showSignIn()
        navigate()
    }

    fun navigateToHome() {
        replaceFragment(HomeFragment())
    }

    fun navigateToMy() {
        val fragment = MyFragment()
        supportFragmentManager.commit {
            add(R.id.main_fcv, fragment)
            addToBackStack(null)
        }
        updateBottomNavVisibility(fragment)
    }

    private fun showSignIn() {
        replaceFragment(SignInFragment())
    }

    private fun navigate() {
        binding.mainBottomNavi.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navi_menu_home -> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.navi_menu_shorts -> {
                    replaceFragment(ShortsFragment())
                    true
                }

                R.id.navi_menu_live -> {
                    replaceFragment(LiveFragment())
                    true
                }

                R.id.navi_menu_search -> {
                    replaceFragment(SearchFragment())
                    true
                }

                R.id.navi_menu_history -> {
                    replaceFragment(HistoryFragment())
                    true
                }

                else -> {
                    Toast.makeText(this, "ERROR OCCURRED", Toast.LENGTH_SHORT).show(); false; }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.main_fcv, fragment)
        }
        updateBottomNavVisibility(fragment)
    }

    private fun updateBottomNavVisibility(fragment: Fragment) {
        val isNavFragment = when (fragment) {
            is HomeFragment,
            is ShortsFragment,
            is LiveFragment,
            is SearchFragment,
            is HistoryFragment -> true

            else -> false
        }

        binding.mainBottomNavi.visibility =
            if (isNavFragment) View.VISIBLE else View.INVISIBLE
    }
}
