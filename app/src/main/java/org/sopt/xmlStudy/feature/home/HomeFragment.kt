package org.sopt.xmlStudy.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.sopt.xmlStudy.databinding.FragmentHomeBinding
import org.sopt.xmlStudy.feature.main.MainActivity
import org.sopt.xmlStudy.feature.mypage.MypageFragment

class HomeFragment : Fragment() {
    private val viewModel by viewModels<HomeViewModel>()
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeSideEffect()
        setLoginButtonClickListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeSideEffect() {
        lifecycleScope.launch {
            viewModel.sideEffect.collectLatest { sideEffect ->
                when (sideEffect) {
                    HomeSideEffect.NavigateToMyPage ->
                        (requireActivity() as? MainActivity)?.replaceFragment(MypageFragment())
                }
            }
        }
    }

    private fun setLoginButtonClickListener() = binding.ivHomeMyProfile.setOnClickListener {
        viewModel.sendIntent(HomeIntent.MyProfileClick)
    }
}