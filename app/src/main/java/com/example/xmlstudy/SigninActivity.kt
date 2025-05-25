package com.example.xmlstudy

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.xmlstudy.databinding.ActivitySigninBinding

class SigninActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySigninBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val signInButton = binding.signInButton

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val idText = binding.editTextId.text.toString().trim()
                val pwText = binding.editTextPw.text.toString().trim()
                signInButton.isEnabled = idText.isNotEmpty() && pwText.isNotEmpty()

            }

            override fun afterTextChanged(s: Editable?) {}
        }

        binding.editTextId.addTextChangedListener(textWatcher)
        binding.editTextPw.addTextChangedListener(textWatcher)

        signInButton.setOnClickListener {
            val id = binding.editTextId.text.toString().trim()
            val pw = binding.editTextPw.text.toString().trim()
            if (id == "lim1234" && pw == "@lim1234") {
                val intent = Intent(this, MyActivity::class.java)
                intent.putExtra("id", id)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "아이디 또는 비밀번호가 적절하지 않습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}