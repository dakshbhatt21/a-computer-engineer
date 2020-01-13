package com.acomputerengineer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_bottom_navigation_view.*


class BottomNavigationViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation_view)

        bnv.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_tab1 -> {
                    tv.text = getString(R.string.str_bnv_tab1)
                    tv.setTextColor(ContextCompat.getColor(this, R.color.colorTab1))
                    bnv.itemIconTintList = ContextCompat.getColorStateList(this, R.color.color_bnv1)
                    bnv.itemTextColor = ContextCompat.getColorStateList(this, R.color.color_bnv1)
                }
                R.id.action_tab2 -> {
                    tv.text = getString(R.string.str_bnv_tab2)
                    tv.setTextColor(ContextCompat.getColor(this, R.color.colorTab2))
                    bnv.itemIconTintList = ContextCompat.getColorStateList(this, R.color.color_bnv2)
                    bnv.itemTextColor = ContextCompat.getColorStateList(this, R.color.color_bnv2)
                }
                R.id.action_tab3 -> {
                    tv.text = getString(R.string.str_bnv_tab3)
                    tv.setTextColor(ContextCompat.getColor(this, R.color.colorTab3))
                    bnv.itemIconTintList = ContextCompat.getColorStateList(this, R.color.color_bnv3)
                    bnv.itemTextColor = ContextCompat.getColorStateList(this, R.color.color_bnv3)
                }
            }
            true
        }

        bnv.selectedItemId = R.id.action_tab1
    }
}
