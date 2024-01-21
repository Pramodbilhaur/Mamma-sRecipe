package com.example.mammasrecipe.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mammasrecipe.R
import com.example.mammasrecipe.RecipeActivity
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
        val recipe = dataList[position]
        holder.binding.recipeName.text = recipe?.tittle
        holder.binding.imageDish.load(recipe?.img){
            placeholder(R.drawable.category_dessert)
        }

        holder.binding.clSearch.setOnClickListener {
            val intent = Intent(context, RecipeActivity::class.java)
            intent.putExtra("RECIPE", recipe)
            context.startActivity(intent)
        }
    }

    inner class SearchViewHolder(val binding: ItemSearchRvBinding): RecyclerView.ViewHolder(binding.root)

    fun filterList(newList: ArrayList<Recipe?>) {
        dataList.clear()
        dataList.addAll(newList)
        notifyDataSetChanged()
    }
}