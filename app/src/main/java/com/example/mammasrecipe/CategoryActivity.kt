package com.example.mammasrecipe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.mammasrecipe.adapter.ExploreCategoryAdapter
import com.example.mammasrecipe.databinding.ActivityCategoryBinding
import com.example.mammasrecipe.model.Categories
import com.example.mammasrecipe.myInterface.CategoryI
import com.example.mammasrecipe.room.AppDatabase
import com.example.mammasrecipe.room.Recipe

class CategoryActivity : AppCompatActivity(){
    lateinit var binding: ActivityCategoryBinding
    lateinit var dataList: ArrayList<Recipe>
    lateinit var exploreCategoryAdapter: ExploreCategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvTitle.text = intent.getStringExtra("TITLE")
        setUpRecyclerView()
        binding.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
            finish()
        }

    }

    fun setUpRecyclerView(){
        dataList = ArrayList()
        var db = Room.databaseBuilder(this, AppDatabase::class.java, "db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()
        var daoObject=db.getDao()
        var recipe = daoObject.getAll()
        recipe?.forEach {
            println(it?.category)
        }
        for(i in recipe!!.indices){
            if(recipe[i]?.category?.contains(intent.getStringExtra("TITLE")!!) == true)
                dataList.add(recipe[i]!!)
        }
        exploreCategoryAdapter = ExploreCategoryAdapter(this, dataList)
        binding.rvSelect.layoutManager = LinearLayoutManager(this)
        binding.rvSelect.adapter = exploreCategoryAdapter
    }


}