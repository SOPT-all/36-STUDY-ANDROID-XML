package com.example.xmlstudy

import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.xmlstudy.databinding.FragmentSigninBinding

class SigninFragment : Fragment() {
    private lateinit var binding: FragmentSigninBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSigninBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val signInButton = binding.signInButton
        val pwEditText = binding.editTextPw
        val pwToggle = binding.pwVisibilityToggle
        var isPwVisible = false

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

        pwToggle.setOnClickListener {
            isPwVisible = !isPwVisible

            pwEditText.inputType =
                if (isPwVisible) InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                else InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

            pwToggle.setImageResource(
                if (isPwVisible) R.drawable.baseline_remove_red_eye_24
                else R.drawable.baseline_visibility_off_24
            )
        }
        signInButton.setOnClickListener { onSignIn() }
    }

    private fun onSignIn() {
        val id = binding.editTextId.text.toString().trim()
        val pw = binding.editTextPw.text.toString().trim()
        if (id == "lim1234" && pw == "@lim1234") {
            (requireActivity() as? HomeActivity)?.navigateToHome()
        } else {
            Toast.makeText(this.activity, "아이디 또는 비밀번호가 적절하지 않습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}