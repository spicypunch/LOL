package com.example.lol

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lol.databinding.ItemViewBinding
import com.example.lol.retrofit.LOLResponse.LOLResponseItem

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    private val itemList = mutableListOf<LOLResponseItem>()

    fun updateList(items: MutableList<LOLResponseItem>) {
        val diffCallback = DiffUtilCallback(itemList, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        itemList.clear()
        itemList.addAll(items)

        diffResult.dispatchUpdatesTo(this)
    }
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
        val data = itemList[position]
        holder.champName.text = "챔피언 코드: "+data.championId.toString()
        holder.champLevel.text = "챔피언 레벨: "+data.championLevel.toString()
        holder.champPoints.text = "챔피언 포인트: "+data.championPoints.toString()
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}