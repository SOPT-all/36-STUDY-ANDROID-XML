package com.purang.xmlstudy.signin

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.InputType
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.purang.xmlstudy.MainActivity
import com.purang.xmlstudy.R
import com.purang.xmlstudy.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private val viewModel: SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.idEdit.addTextChangedListener {
            viewModel.fetchEmail(it.toString())
        }

        binding.passwordEdit.addTextChangedListener {
            viewModel.fetchPassword(it.toString())
        }

        binding.passwordMode.setOnClickListener {
            val isPasswordVisible = binding.passwordEdit.inputType and InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD

            if (isPasswordVisible) {
                binding.passwordEdit.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.passwordMode.setImageResource(R.drawable.eye_off)
            } else {
                binding.passwordEdit.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                binding.passwordMode.setImageResource(R.drawable.baseline_remove_red_eye_24)
            }

            binding.passwordEdit.setSelection(binding.passwordEdit.text?.length ?: 0)
        }

        viewModel.isLoginEnabled.observe(this) { isEnabled ->
            buttonStatus(isEnabled)
            binding.loginButton.isEnabled = isEnabled
        }

        binding.loginButton.setOnClickListener {
            if (viewModel.validateSignIn()) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun buttonStatus(isEnabled: Boolean) {
        val button = binding.loginButton

        if (isEnabled) {
            button.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(this, R.color.dark_brand)
            )
            button.setTextColor(ContextCompat.getColor(this, android.R.color.white))
        } else {
            button.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(this, R.color.dark_button)
            )
            button.setTextColor(ContextCompat.getColor(this, R.color.dark_gray2))
        }
    }
}

