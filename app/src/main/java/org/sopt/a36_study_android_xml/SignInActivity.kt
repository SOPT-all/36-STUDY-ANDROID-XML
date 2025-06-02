package org.sopt.a36_study_android_xml

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.sopt.a36_study_android_xml.databinding.ActivitySignInBinding
import androidx.core.graphics.toColorInt

class SignInActivity : AppCompatActivity() {

    private val binding: ActivitySignInBinding by lazy {
        ActivitySignInBinding.inflate(layoutInflater)
    }

    private val viewModel: SignInViewmodel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSignInState()
        onClickLoginButton()
        setUi()
    }

    private fun setSignInState() {
        with(binding){
            edittextId.doAfterTextChanged {
                viewModel.updateId(it.toString())
            }

            edittextPassword.doAfterTextChanged {
                viewModel.updatePassword(it.toString())
            }
        }
    }

    private fun onClickLoginButton() {
        with(binding.signInButton){
            setOnClickListener {
                if (viewModel.navigateToMain()) {
                    startActivity(Intent(this@SignInActivity, MainActivity::class.java))
                } else {
                    Toast.makeText(this@SignInActivity, "아이디와 비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setUi() {
        with(binding) {
            lifecycleScope.launch {
                viewModel.signInState.collect{ state ->
                    signInButton.isEnabled = state.isButtonEnabled
                    if(state.isButtonEnabled){
                        signInButton.backgroundTintList =
                            ColorStateList.valueOf(Color.parseColor("#FF143C"))
                    } else {
                        signInButton.backgroundTintList =
                            ColorStateList.valueOf(Color.parseColor("#323232"))
                    }
                }
            }
        }
    }
}