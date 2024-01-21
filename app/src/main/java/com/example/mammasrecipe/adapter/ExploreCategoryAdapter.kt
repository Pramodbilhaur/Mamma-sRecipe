package com.example.mammasrecipe.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mammasrecipe.R
import com.example.mammasrecipe.databinding.ItemSelectCategoryBinding
import com.example.mammasrecipe.room.Recipe

class ExploreCategoryAdapter(val context: Context, var dataList: ArrayList<Recipe>): RecyclerView.Adapter<ExploreCategoryAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExploreCategoryAdapter.MyViewHolder {
        val binding = ItemSelectCategoryBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExploreCategoryAdapter.MyViewHolder, position: Int) {
        with(holder.binding){
            ivDish.load(dataList[position].img){
                placeholder(R.drawable.developer_ic)
            }
            tvDishName.text = dataList[position].tittle
            var temp = dataList[position].ing.split("\n").dropLastWhile { it.isEmpty() }.toTypedArray()
            tvDuration.text = temp[0]
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class MyViewHolder(val binding: ItemSelectCategoryBinding) : RecyclerView.ViewHolder(binding.root)
}