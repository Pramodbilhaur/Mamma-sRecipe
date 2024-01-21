package com.example.mammasrecipe

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.mammasrecipe.adapter.CategoryAdapter
import com.example.mammasrecipe.adapter.PopularAdapter
import com.example.mammasrecipe.databinding.ActivityHomeBinding
import com.example.mammasrecipe.model.Categories
import com.example.mammasrecipe.myInterface.CategoryI
import com.example.mammasrecipe.room.AppDatabase
import com.example.mammasrecipe.room.Recipe
import kotlin.collections.ArrayList

class HomeActivity : AppCompatActivity(), CategoryI {
    var categoryList = arrayListOf<Categories>()
    lateinit var categoryAdapter: CategoryAdapter
    lateinit var popularAdapter: PopularAdapter
    lateinit var binding: ActivityHomeBinding
    lateinit var dataList: ArrayList<Recipe>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createCategoryList()

        categoryAdapter = CategoryAdapter(this, categoryList, this)
        binding.rvCategory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCategory.adapter = categoryAdapter
        setUpRecyclerView()
        binding.etSearch.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
    }

    fun setUpRecyclerView(){
        dataList = ArrayList()
        var db = Room.databaseBuilder(this@HomeActivity, AppDatabase::class.java, "db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()
        var daoObject=db.getDao()
        var recipe = daoObject.getAll()
        for(i in recipe!!.indices){
            if(recipe[i]?.category == "Popular")
                dataList.add(recipe[i]!!)
        }
        popularAdapter = PopularAdapter(this, dataList)
        binding.rvPopularCat.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvPopularCat.adapter = popularAdapter
    }

    private fun createCategoryList(){
        categoryList.add(Categories(R.drawable.category_salad, "Salad"))
        categoryList.add(Categories(R.drawable.category_main, "Dish"))
        categoryList.add(Categories(R.drawable.drinks, "Drinks"))
        categoryList.add(Categories(R.drawable.category_dessert, "Dessert"))
    }

    override fun getPosition(category: Categories) {
        val intent = Intent(this, CategoryActivity::class.java)
        intent.putExtra("TITLE", category.text)
        startActivity(intent)
    }
}