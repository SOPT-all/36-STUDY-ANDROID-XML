package org.sopt.xmlStudy.feature.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.sopt.xmlStudy.R
import org.sopt.xmlStudy.databinding.FragmentSigninBinding
import org.sopt.xmlStudy.feature.home.HomeFragment
import org.sopt.xmlStudy.feature.main.MainActivity

class SignInFragment : Fragment() {
    private val viewModel by viewModels<SignInViewModel>()
    private var _binding: FragmentSigninBinding? = null
    private val binding: FragmentSigninBinding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSigninBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setIdTextField()
        setPasswordTextField()
        setLoginButtonClickListener()
        observeSideEffect()
        observeUiState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeSideEffect() {
        lifecycleScope.launch {
            viewModel.sideEffect.collectLatest { sideEffect ->
                when (sideEffect) {
                    SignInSideEffect.NavigateToLogin -> {
                        (requireActivity() as? MainActivity)?.replaceFragment(HomeFragment())
                    }

                    SignInSideEffect.FailureLogin -> {
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
            viewModel.sendIntent(SignInIntent.UpdateId(it.toString()))
        }
    }

    private fun setPasswordTextField() = binding.editTextPassword.apply {
        doAfterTextChanged {
            viewModel.sendIntent(SignInIntent.UpdatePw(it.toString()))
        }
    }

    private fun setLoginButtonClickListener() = binding.btnButton.setOnClickListener {
        viewModel.sendIntent(SignInIntent.Login)
    }
}