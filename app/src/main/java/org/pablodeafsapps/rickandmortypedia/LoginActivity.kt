package org.pablodeafsapps.rickandmortypedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import org.pablodeafsapps.rickandmortypedia.databinding.ActivityLoginBinding
import org.pablodeafsapps.rickandmortypedia.databinding.ActivityMainBinding
import org.pablodeafsapps.rickandmortypedia.main.presentation.view.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        binding.btnLogin.setOnClickListener {
            val email: Email = Email(value = binding.etEmail.text.toString())
            val keypass: Keypass = Keypass(value = binding.etKeypass.text.toString())
            onLoginButtonSelected(email = email, keypass = keypass)
        }
    }

    private fun onLoginButtonSelected(email: Email, keypass: Keypass) {
        if (email.isValid() && keypass.isValid()) {
            // Firebase
            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    val fbInstance = FirebaseAuth.getInstance()
                    try {
                        fbInstance.signInWithEmailAndPassword(email.value, keypass.value).await()
                            .runCatching { user?.email }
                            .onSuccess {
                                // navigate to 'MainActivity'
                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                startActivity(intent)
                            }
                            .onFailure {
                                Toast.makeText(this@LoginActivity, "Firebase error", Toast.LENGTH_SHORT).show()
                            }
                    } catch(e: Exception) {
                        withContext(Dispatchers.Main) {
                            println(e.message)
                            Toast.makeText(this@LoginActivity, e.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        } else {
            Toast.makeText(this, "Login data not valid", Toast.LENGTH_SHORT).show()
        }
    }

}

private fun Email.isValid(): Boolean {
    val pattern = """.+@.+\.com""".toRegex()
    return value.contains(pattern)
}

private const val KEYPASS_LENGTH: Int = 5
private fun Keypass.isValid(): Boolean = value.length > KEYPASS_LENGTH

@JvmInline
value class Email(val value: String)

@JvmInline
value class Keypass(val value: String)
