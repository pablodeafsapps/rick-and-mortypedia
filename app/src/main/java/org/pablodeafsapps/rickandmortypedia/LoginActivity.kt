package org.pablodeafsapps.rickandmortypedia

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import org.pablodeafsapps.rickandmortypedia.databinding.ActivityLoginBinding
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
                                withContext(Dispatchers.Main) {
                                    Toast.makeText(this@LoginActivity, "Firebase error", Toast.LENGTH_SHORT).show()
                                }
                            }
                    } catch(e: Exception) {
                        withContext(Dispatchers.Main) {
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
