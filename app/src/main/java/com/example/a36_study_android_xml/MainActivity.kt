package com.example.a36_study_android_xml

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.a36_study_android_xml.databinding.ActivityMainBinding
import com.example.a36_study_android_xml.databinding.FragmentHomeBinding

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
        navigate()
        //replaceLoginPage()
    }

    private fun replaceLoginPage() {
        supportFragmentManager.commit {
            replace(R.id.main_fcv, SignInFragment())
        }
    }

    private fun navigate() {
        binding.mainBottomNavi.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.navi_menu_home -> {
                    supportFragmentManager.commit {
                        replace(R.id.main_fcv, HomeFragment())
                    }
                    true
                }
                R.id.navi_menu_shorts -> {
                    supportFragmentManager.commit {
                        replace(R.id.main_fcv, ShortsFragment())
                    }
                    true
                }
                R.id.navi_menu_live -> {
                    supportFragmentManager.commit {
                        replace(R.id.main_fcv, LiveFragment())
                    }
                    true
                }
                R.id.navi_menu_search -> {
                    supportFragmentManager.commit {
                        replace(R.id.main_fcv, SearchFragment())
                    }
                    true
                }
                R.id.navi_menu_history -> {
                    supportFragmentManager.commit {
                        replace(R.id.main_fcv, HistoryFragment())
                    }
                    true
                }
                else -> { Toast.makeText(this, "ERROR OCCURRED", Toast.LENGTH_SHORT).show(); false; }
            }
        }
    }
}