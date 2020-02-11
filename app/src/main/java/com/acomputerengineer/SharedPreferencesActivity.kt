package com.acomputerengineer

import android.os.Bundle
import android.text.Editable
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.acomputerengineer.Utils.AppPreferences
import kotlinx.android.synthetic.main.activity_bottom_navigation_view.tv
import kotlinx.android.synthetic.main.activity_shared_preferences.*


class SharedPreferencesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preferences)

        setupLoginLayout()

        btnLogin.setOnClickListener {
            if (AppPreferences.isLogin) {
                AppPreferences.isLogin = false
                AppPreferences.username = ""
                AppPreferences.password = ""
            } else {
                val username = etUsername.text.toString()
                val password = etPassword.text.toString()
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
            tv.text = getString(R.string.welcome_note, AppPreferences.username)
            etUsername.visibility = GONE
            etPassword.visibility = GONE
            btnLogin.text = getString(R.string.logout)
        } else {
            tv.text = getString(R.string.login_note)
            etUsername.visibility = VISIBLE
            etPassword.visibility = VISIBLE
            btnLogin.text = getString(R.string.login)
        }
    }
}
