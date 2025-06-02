package com.example.xmlstudy

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.xmlstudy.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (savedInstanceState == null) {
            val fragment = SigninFragment()
            supportFragmentManager.commit {
                replace(R.id.fragment_container_view, fragment)
            }
            updateBottomNavVisibility(fragment)
        }

        supportFragmentManager.addOnBackStackChangedListener {
            val currentFragment =
                supportFragmentManager.findFragmentById(R.id.fragment_container_view)
            currentFragment?.let { updateBottomNavVisibility(it) }
        }

        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.menu_shorts -> {
                    replaceFragment(ShortsFragment())
                    true
                }

                R.id.menu_live -> {
                    replaceFragment(LiveFragment())
                    true
                }

                R.id.menu_search -> {
                    replaceFragment(SearchFragment())
                    true
                }

                R.id.menu_history -> {
                    replaceFragment(HistoryFragment())
                    true
                }

                else -> {
                    Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                    false
                }
            }
        }
    }

    fun navigateToHome() {
        replaceFragment(HomeFragment())
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.fragment_container_view, fragment)
        }
        updateBottomNavVisibility(fragment)
    }

    private fun updateBottomNavVisibility(fragment: Fragment) {
        val isVisible =
            when (fragment) {
                is HomeFragment,
                is ShortsFragment,
                is SearchFragment,
                is LiveFragment,
                is HistoryFragment -> true

                else -> false
            }
        binding.bottomNav.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
    }
}