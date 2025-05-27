package org.sopt.xmlStudy.feature.main

import android.content.Intent
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
import org.sopt.xmlStudy.R
import org.sopt.xmlStudy.databinding.ActivityMainBinding
import org.sopt.xmlStudy.feature.mypage.MyPageActivity

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

        setIdTextField()
        setPasswordTextField()
        setLoginButtonClickListener()
        observeSideEffect()
        observeUiState()
    }

    private fun observeSideEffect() {
        lifecycleScope.launch {
            viewModel.sideEffect.collectLatest { sideEffect ->
                when (sideEffect) {
                    MainSideEffect.NavigateToLogin -> {
                        val intent = Intent(this@MainActivity, MyPageActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

                    MainSideEffect.FailureLogin -> {
                        Toast.makeText(this@MainActivity, "로그인 실패", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun observeUiState() {
        lifecycleScope.launch {
            viewModel.uiState.collectLatest { state ->
                val enabled = state.id.isNotBlank() && state.password.isNotBlank()
                updateLoginButtonState(enabled)
            }
        }
    }

    private fun updateLoginButtonState(enabled: Boolean) = binding.btnButton.run {
        isEnabled = enabled
        val bgColorRes = if (enabled) R.color.buttonSuccess else R.color.buttonBackground
        val textColorRes = if (enabled) R.color.white else R.color.lightGray

        setBackgroundColor(ContextCompat.getColor(context, bgColorRes))
        setTextColor(ContextCompat.getColor(context, textColorRes))
    }

    private fun setIdTextField() = binding.editTextId.apply {
        doAfterTextChanged {
            viewModel.sendIntent(MainIntent.UpdateId(it.toString()))
        }
    }

    private fun setPasswordTextField() = binding.editTextPassword.apply {
        doAfterTextChanged {
            viewModel.sendIntent(MainIntent.UpdatePw(it.toString()))
        }
    }

    private fun setLoginButtonClickListener() = binding.btnButton.setOnClickListener {
        viewModel.sendIntent(MainIntent.Login)
    }
}