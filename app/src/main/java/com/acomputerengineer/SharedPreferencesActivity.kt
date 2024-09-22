package com.acomputerengineer

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.acomputerengineer.Utils.AppPreferences
import com.acomputerengineer.databinding.ActivitySharedPreferencesBinding

class SharedPreferencesActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySharedPreferencesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySharedPreferencesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupLoginLayout()

        binding.btnLogin.setOnClickListener {
            if (AppPreferences.isLogin) {
                AppPreferences.isLogin = false
                AppPreferences.username = ""
                AppPreferences.password = ""
            } else {
                val username = binding.etUsername.text.toString()
                val password = binding.etPassword.text.toString()
                if (username.isNotBlank() && password.isNotBlank()) {
                    AppPreferences.isLogin = true
                    AppPreferences.username = username
                    AppPreferences.password = password
                } else {
                    Toast.makeText(this, R.string.login_validation, Toast.LENGTH_SHORT).show()
                }
            }
            setupLoginLayout()
        }
    }

    private fun setupLoginLayout() {
        if (AppPreferences.isLogin) {
            binding.tv.text = getString(R.string.welcome_note, AppPreferences.username)
            binding.etUsername.visibility = GONE
            binding.etPassword.visibility = GONE
            binding.btnLogin.text = getString(R.string.logout)
        } else {
            binding.tv.text = getString(R.string.login_note)
            binding.etUsername.visibility = VISIBLE
            binding.etPassword.visibility = VISIBLE
            binding.btnLogin.text = getString(R.string.login)
        }
    }
}
