package com.acomputerengineer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.acomputerengineer.Adapters.ImageGridKotlinAdapter
import kotlinx.android.synthetic.main.activity_image_grid_kotlin.*
import java.util.*

class ImageGridKotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_grid_kotlin)

        val sglm = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rv.layoutManager = sglm

        val imageList = ArrayList<String>()
        imageList.add("https://picsum.photos/id/111/200")
        imageList.add("https://picsum.photos/id/212/200")
        imageList.add("https://picsum.photos/id/313/200")
        imageList.add("https://picsum.photos/id/435/200")
        imageList.add("https://picsum.photos/id/142/200")
        imageList.add("https://picsum.photos/id/123/200")
        imageList.add("https://picsum.photos/id/321/200")
        imageList.add("https://picsum.photos/id/156/200")
        imageList.add("https://picsum.photos/id/189/200")
        imageList.add("https://picsum.photos/id/10/200")
        imageList.add("https://picsum.photos/id/11/200")
        imageList.add("https://picsum.photos/id/12/200")
        imageList.add("https://picsum.photos/id/13/200")
        imageList.add("https://picsum.photos/id/14/200")
        imageList.add("https://picsum.photos/id/15/200")
        imageList.add("https://picsum.photos/id/16/200")
        imageList.add("https://picsum.photos/id/17/200")
        val igka = ImageGridKotlinAdapter(this, imageList)
        rv.adapter = igka
    }

}
