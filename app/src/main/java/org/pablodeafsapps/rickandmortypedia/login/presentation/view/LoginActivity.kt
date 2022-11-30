package org.pablodeafsapps.rickandmortypedia.login.presentation.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import org.pablodeafsapps.rickandmortypedia.RickAndMortyApplication
import org.pablodeafsapps.rickandmortypedia.databinding.ActivityLoginBinding
import org.pablodeafsapps.rickandmortypedia.login.di.LoginComponent
import org.pablodeafsapps.rickandmortypedia.login.domain.model.Email
import org.pablodeafsapps.rickandmortypedia.login.domain.model.Keypass
import org.pablodeafsapps.rickandmortypedia.login.domain.model.LoginUser
import org.pablodeafsapps.rickandmortypedia.login.domain.utils.isValid
import org.pablodeafsapps.rickandmortypedia.login.presentation.viewmodel.LoginViewModel
import org.pablodeafsapps.rickandmortypedia.main.presentation.view.MainActivity
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var loginViewModel: LoginViewModel

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        getLoginComponent().inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        subscribeToDataFlows()
    }

    private fun initViews() {
        binding.btnLogin.setOnClickListener {
            val email = Email(value = binding.etEmail.text.toString())
            val keypass = Keypass(value = binding.etKeypass.text.toString())
            loginViewModel.onLoginOptionSelected(email = email, keypass = keypass)
        }
    }

    private fun subscribeToDataFlows() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.loginUser.collect { user -> handleResult(result = user) }
            }
        }

    }

    private fun handleResult(result: LoginUser?) {
        if (result != null) {
            if (result.isValid()) {
                navigateToMainActivity()
            } else {
                println("Error: no user detected, login failed")
                Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }

}

private fun LoginActivity.getLoginComponent(): LoginComponent =
    (application as RickAndMortyApplication).providesLoginComponent()
