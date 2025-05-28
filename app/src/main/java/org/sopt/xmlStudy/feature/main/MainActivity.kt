package org.sopt.xmlStudy.feature.main

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import org.sopt.xmlStudy.R
import org.sopt.xmlStudy.databinding.ActivityMainBinding
import org.sopt.xmlStudy.feature.home.HomeFragment
import org.sopt.xmlStudy.feature.signin.SignInFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                0
            )
            insets
        }

        initBottomNavigationClickListener()
        replaceFragment(SignInFragment())
    }

    private fun initBottomNavigationClickListener() {
        binding.bnvMain.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.navigation_shorts -> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.navigation_live -> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.navigation_search -> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.navigation_history -> {
                    replaceFragment(HomeFragment())
                    true
                }

                else -> false
            }
        }
    }

    internal fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_main, fragment)
            .commit()

        if (fragment is SignInFragment) {
            showBottomNav(false)
        } else {
            showBottomNav(true)
        }
    }

    fun showBottomNav(show: Boolean) {
        binding.bnvMain.visibility = if (show) View.VISIBLE else View.GONE
    }
}