package com.example.lol

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lol.databinding.ItemViewBinding

class RecyclerViewAdapter() : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    class MyViewHolder(binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        val champImg = binding.champImg
        val champName = binding.champName
        val champLevel = binding.champLevel
        val champPoints = binding.champPoints

        val root = binding.root
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ItemViewBinding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data =
    }
}