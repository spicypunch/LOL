package com.example.lol

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lol.databinding.ItemViewBinding
import com.example.lol.retrofit.LOLResponseItem

class RecyclerViewAdapter(private val List: MutableList<LOLResponseItem>) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    class MyViewHolder(binding: ItemViewBinding){
//        val champImg = binding.champImg
        val champName = binding.champName
        val champLevel = binding.champLevel
        val champPoints = binding.champPoints
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ItemViewBinding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = List[position]
        holder.champName.text = data.championId.toString()
        holder.champLevel.text = data.championLevel.toString()
        holder.champPoints.text = data.championPoints.toString()
    }

    override fun getItemCount(): Int {
        return List.size
    }
}