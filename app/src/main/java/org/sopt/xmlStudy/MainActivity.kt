package org.sopt.xmlStudy

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.sopt.xmlStudy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

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
                systemBars.bottom
            )
            insets
        }

        setIdTextFiled()
        setPasswordTextFiled()
        setLoginBtnClickListener()
        observeUiState()
    }

    private fun observeUiState() {
        lifecycleScope.launch {
            viewModel.uiState.collectLatest { state ->
                val enabled = viewModel.navigateToHome()
                binding.btnButton.apply {
                    isEnabled = enabled
                    setBackgroundColor(
                        ContextCompat.getColor(
                            this@MainActivity,
                            if (enabled) R.color.buttonSuccess else R.color.buttonBackground
                        )
                    )
                    setTextColor(
                        ContextCompat.getColor(
                            this@MainActivity,
                            if (enabled) R.color.white else R.color.lightGray
                        )
                    )
                }
            }
        }
    }

    private fun setIdTextFiled() = binding.editTextId.apply {
        doAfterTextChanged { viewModel.updateId(it.toString()) }
        setText(viewModel.uiState.value.id)
    }

    private fun setPasswordTextFiled() = binding.editTextPassword.apply {
        doAfterTextChanged { viewModel.updatePassword(it.toString()) }
        setText(viewModel.uiState.value.password)
    }

    private fun setLoginBtnClickListener() {
        binding.btnButton.setOnClickListener {
            Toast.makeText(
                this,
                "id: ${viewModel.uiState.value.id}, pw: ${viewModel.uiState.value.password}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}