package com.example.a36_study_android_xml

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.a36_study_android_xml.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

        binding.siginLoginBtn.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                if (isValid) android.R.color.white else R.color.gray500
            )
        )
        binding.siginLoginBtn.backgroundTintList =
            ColorStateList.valueOf(
                ContextCompat.getColor(
                    requireContext(),
                    if (isValid) R.color.tintRed else R.color.gray800
                )
            )
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
        Toast.makeText(this.activity, message, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToMain() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

}