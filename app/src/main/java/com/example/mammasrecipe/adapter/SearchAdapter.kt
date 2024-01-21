package com.example.mammasrecipe.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mammasrecipe.databinding.ItemSearchRvBinding
import com.example.mammasrecipe.room.Recipe

class SearchAdapter(private val context: Context, private var dataList: ArrayList<Recipe?>):RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ItemSearchRvBinding.inflate(LayoutInflater.from(context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    inner class SearchViewHolder(val binding: ItemSearchRvBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(recipe: Recipe?) {
            binding.recipeName.text = recipe?.tittle
            binding.imageDish.load(recipe?.img)
        }
    }

    fun filterList(newList: ArrayList<Recipe?>) {
        dataList.clear()
        dataList.addAll(newList)
        notifyDataSetChanged()
    }
}