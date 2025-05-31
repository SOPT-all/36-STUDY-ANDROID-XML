package com.example.a36_study_android_xml

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.example.a36_study_android_xml.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

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

        initListener()
        updateLoginButtonState()
    }

    private fun initListener() {
        binding.siginIdEdittext.addTextChangedListener { updateLoginButtonState() }
        binding.siginPwEdittext.addTextChangedListener { updateLoginButtonState() }

        binding.siginLoginBtn.setOnClickListener { handleLogin() }
    }

    private fun updateLoginButtonState() {
        val id = binding.siginIdEdittext.text.toString()
        val pw = binding.siginPwEdittext.text.toString()
        val isValid = id.isNotBlank() && pw.isNotBlank()

        binding.siginLoginBtn.isEnabled = isValid

        binding.siginLoginBtn.setTextColor(getColor(if (isValid) android.R.color.white else R.color.gray500))
        binding.siginLoginBtn.backgroundTintList =
            ColorStateList.valueOf(getColor(if (isValid) R.color.tintRed else R.color.gray800))
    }


    private fun handleLogin() {
        val id = binding.siginIdEdittext.text.toString()
        val pw = binding.siginPwEdittext.text.toString()

        if (id == "tving123" && pw == "password") {
            showToast("로그인 성공!")
            navigateToMain()
        } else {
            showToast("아이디 또는 비밀번호가 올바르지 않습니다.")
        }

    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
