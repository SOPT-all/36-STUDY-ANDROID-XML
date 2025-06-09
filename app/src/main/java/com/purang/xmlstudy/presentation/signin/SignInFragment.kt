package com.purang.xmlstudy.presentation.signin

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.purang.xmlstudy.R
import com.purang.xmlstudy.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SignInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.idEdit.addTextChangedListener {
            viewModel.fetchEmail(it.toString())
        }
        binding.passwordEdit.addTextChangedListener {
            viewModel.fetchPassword(it.toString())
        }

        binding.passwordMode.setOnClickListener {
            val isPasswordVisible =
                binding.passwordEdit.inputType and InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD ==
                        InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD

            if (isPasswordVisible) {
                binding.passwordEdit.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.passwordMode.setImageResource(R.drawable.eye_off)
            } else {
                binding.passwordEdit.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                binding.passwordMode.setImageResource(R.drawable.baseline_remove_red_eye_24)
            }

            binding.passwordEdit.setSelection(binding.passwordEdit.text?.length ?: 0)
        }

        viewModel.isLoginEnabled.observe(viewLifecycleOwner) { isEnabled ->
            setButtonStatus(isEnabled)
            binding.loginButton.isEnabled = isEnabled
        }


        binding.loginButton.setOnClickListener {
            if (viewModel.validateSignIn()) {
                findNavController().navigate(
                    R.id.action_signInFragment_to_homeFragment
                )
                (activity as? com.purang.xmlstudy.presentation.main.MainActivity)
                    ?.showBottomNav()
            } else {
                Toast.makeText(requireContext(), "로그인 실패", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setButtonStatus(isEnabled: Boolean) {
        val button = binding.loginButton
        if (isEnabled) {
            button.backgroundTintList =
                ContextCompat.getColorStateList(requireContext(), R.color.dark_brand)
            button.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.white))
        } else {
            button.backgroundTintList =
                ContextCompat.getColorStateList(requireContext(), R.color.dark_button)
            button.setTextColor(ContextCompat.getColor(requireContext(), R.color.dark_gray2))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
