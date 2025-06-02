package com.example.xmlstudy

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.commit
import com.example.xmlstudy.databinding.ActivityHomeBinding
import com.example.xmlstudy.ui.theme.XMLSTUDYTheme

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.fragment_home)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    supportFragmentManager.commit {
                        replace(R.id.fragment_container_view, HomeFragment())
                    }
                    true
                }

                R.id.menu_shorts -> {
                    supportFragmentManager.commit {
                        replace(R.id.fragment_container_view, ShortsFragment())
                    }
                    true
                }

                R.id.menu_live -> {
                    supportFragmentManager.commit {
                        replace(R.id.fragment_container_view, LiveFragment())
                    }
                    true
                }

                R.id.menu_search -> {
                    supportFragmentManager.commit {
                        replace(R.id.fragment_container_view, SearchFragment())
                    }
                    true
                }

                R.id.menu_history -> {
                    supportFragmentManager.commit {
                        replace(R.id.fragment_container_view, HistoryFragment())
                    }
                    true
                }

                else -> {
                    Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                    false
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    XMLSTUDYTheme {
        Greeting2("Android")
    }
}