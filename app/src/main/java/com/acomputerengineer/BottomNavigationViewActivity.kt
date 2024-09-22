package com.acomputerengineer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.acomputerengineer.databinding.ActivityBottomNavigationViewBinding


class BottomNavigationViewActivity : AppCompatActivity() {


    private lateinit var binding: ActivityBottomNavigationViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavigationViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bnv.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_tab1 -> {
                    binding.tv.text = getString(R.string.str_bnv_tab1)
                    binding.tv.setTextColor(ContextCompat.getColor(this, R.color.colorTab1))
                    binding.bnv.itemIconTintList = ContextCompat.getColorStateList(this, R.color.color_bnv1)
                    binding.bnv.itemTextColor = ContextCompat.getColorStateList(this, R.color.color_bnv1)
                }

                R.id.action_tab2 -> {
                    binding.tv.text = getString(R.string.str_bnv_tab2)
                    binding.tv.setTextColor(ContextCompat.getColor(this, R.color.colorTab2))
                    binding.bnv.itemIconTintList = ContextCompat.getColorStateList(this, R.color.color_bnv2)
                    binding.bnv.itemTextColor = ContextCompat.getColorStateList(this, R.color.color_bnv2)
                }

                R.id.action_tab3 -> {
                    binding.tv.text = getString(R.string.str_bnv_tab3)
                    binding.tv.setTextColor(ContextCompat.getColor(this, R.color.colorTab3))
                    binding.bnv.itemIconTintList = ContextCompat.getColorStateList(this, R.color.color_bnv3)
                    binding.bnv.itemTextColor = ContextCompat.getColorStateList(this, R.color.color_bnv3)
                }
            }
            true
        }

        binding.bnv.selectedItemId = R.id.action_tab1
    }

}
