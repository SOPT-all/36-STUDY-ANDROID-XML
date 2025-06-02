package org.sopt.a36_study_android_xml

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.sopt.a36_study_android_xml.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, HomeFragment())
            .commit()
        initBottomBar()
    }

    private fun initBottomBar() {
        with(binding){
            navigationBar.setOnItemSelectedListener {
                when(it.itemId){
                    R.id.home -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, HomeFragment())
                            .commit()
                        true
                    }
                    R.id.shorts -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, ShortsFragment())
                            .commit()
                        true
                    }
                    R.id.live -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, LiveFragment())
                            .commit()
                        true
                    }
                    R.id.search -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, SearchFragment())
                            .commit()
                        true
                    }
                    R.id.history -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, HistoryFragment())
                            .commit()
                        true
                    }

                    else -> false
                }
            }
        }
    }
}