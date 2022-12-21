package com.example.lol

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lol.databinding.ItemViewBinding
import com.example.lol.retrofit.LOLResponse.LOLResponseItem

class RecyclerViewAdapter(private val list: MutableList<LOLResponseItem>) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

//    private val list = mutableListOf<LOLResponseItem>()

    class MyViewHolder(binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        val champName = binding.champName
        val champLevel = binding.champLevel
        val champPoints = binding.champPoints
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ItemViewBinding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = list[position]
        holder.champName.text = "챔피언 코드: "+data.championId.toString()
        holder.champLevel.text = "챔피언 레벨: "+data.championLevel.toString()
        holder.champPoints.text = "챔피언 포인트: "+data.championPoints.toString()
    }

    override fun getItemCount(): Int {
        return list.size
    }
}