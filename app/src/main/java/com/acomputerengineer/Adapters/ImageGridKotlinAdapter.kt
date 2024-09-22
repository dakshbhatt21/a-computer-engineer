package com.acomputerengineer.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.acomputerengineer.databinding.ItemGridKotlinBinding
import com.squareup.picasso.Picasso

class ImageGridKotlinAdapter(private val c: Context, private val images: ArrayList<String>) :
    RecyclerView.Adapter<ImageGridKotlinAdapter.ColorViewHolder>() {


    override fun getItemCount(): Int {
        return images.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val binding = ItemGridKotlinBinding.inflate(LayoutInflater.from(c), parent, false)
        return ColorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        val path = images[position]

        Picasso.get().load(path).resize(250, 250).centerCrop().into(holder.binding.iv)

        holder.binding.iv.setOnClickListener {
            //handle click event on image
        }
    }

    class ColorViewHolder(val binding: ItemGridKotlinBinding) : RecyclerView.ViewHolder(binding.root)

}
