package com.acomputerengineer

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.acomputerengineer.databinding.ActivityOpenWhatsappNumberBinding

class OpenWhatsappNumberActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOpenWhatsappNumberBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOpenWhatsappNumberBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn.setOnClickListener {
            val phoneNumber = binding.et.text.toString()
            val url = "https://api.whatsapp.com/send?phone=$phoneNumber"
            try {
                packageManager.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES)
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
            } catch (e: PackageManager.NameNotFoundException) {
                Toast.makeText(this, "Whatsapp is not installed in your phone.", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }
    }
}
