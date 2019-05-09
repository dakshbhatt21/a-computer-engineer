package com.acomputerengineer.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.acomputerengineer.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_grid_kotlin.view.*

class ImageGridKotlinAdapter(private val c: Context, private val images: ArrayList<String>) :
        RecyclerView.Adapter<ImageGridKotlinAdapter.ColorViewHolder>() {


    override fun getItemCount(): Int {
        return images.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        return ColorViewHolder(LayoutInflater.from(c).inflate(R.layout.item_grid_kotlin, parent, false))
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        val path = images[position]

        Picasso.get()
                .load(path)
                .resize(250, 250)
                .centerCrop()
                .into(holder.iv)

        holder.iv.setOnClickListener {
            //handle click event on image
        }
    }

    class ColorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val iv = view.iv as ImageView
    }
}