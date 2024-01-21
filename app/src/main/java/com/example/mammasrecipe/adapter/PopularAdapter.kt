package com.example.mammasrecipe.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mammasrecipe.R
import com.example.mammasrecipe.RecipeActivity
import com.example.mammasrecipe.databinding.ItemPopularCategoriesBinding
import com.example.mammasrecipe.room.Recipe

class PopularAdapter(val context: Context, val dataList: List<Recipe>): RecyclerView.Adapter<PopularAdapter.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemBinding = ItemPopularCategoriesBinding.inflate(LayoutInflater.from(context),parent, false)
        return MyViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val recipe = dataList[position]
        holder.itemBinding.popularImage
            .load(dataList[position].img){
                placeholder(R.drawable.ic_launcher_background)
            }
        holder.itemBinding.popularText.text = dataList[position].tittle
        var time = dataList[position].ing.split("\n".toRegex()).dropLastWhile {
            it.isEmpty()
        }.toTypedArray()
        holder.itemBinding.popularTime.text = time[0]

        holder.itemBinding.popularImage.setOnClickListener {
            val intent = Intent(context, RecipeActivity::class.java)
            intent.putExtra("RECIPE", recipe)
            context.startActivity(intent)
        }
    }

    inner class MyViewHolder(var itemBinding: ItemPopularCategoriesBinding): RecyclerView.ViewHolder(itemBinding.root) {

    }
}