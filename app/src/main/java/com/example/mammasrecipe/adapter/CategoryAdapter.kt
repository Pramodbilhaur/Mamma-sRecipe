package com.example.mammasrecipe.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mammasrecipe.CategoryActivity
import com.example.mammasrecipe.databinding.ItemViewCategoryBinding
import com.example.mammasrecipe.model.Categories
import com.example.mammasrecipe.myInterface.CategoryI

class CategoryAdapter(val context: Context, private val categoryList: ArrayList<Categories>,val categoryI: CategoryI): RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemBinding = ItemViewCategoryBinding.inflate(LayoutInflater.from(parent.context))
        return MyViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val categoryBind = categoryList[position]
        holder.itemBinding.categoryImage.setImageResource(categoryBind.imageResource)
        holder.itemBinding.categoryName.text = categoryBind.text
        holder.itemBinding.clCategory.setOnClickListener {
            categoryI.getPosition(categoryBind)
        }
    }


    inner class MyViewHolder(val itemBinding: ItemViewCategoryBinding): RecyclerView.ViewHolder(itemBinding.root)
}