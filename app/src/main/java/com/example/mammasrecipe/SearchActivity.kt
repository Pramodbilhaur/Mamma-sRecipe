package com.example.mammasrecipe

import android.annotation.SuppressLint
import android.inputmethodservice.InputMethodService
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.mammasrecipe.adapter.SearchAdapter
import com.example.mammasrecipe.databinding.ActivitySearchBinding
import com.example.mammasrecipe.room.AppDatabase
import com.example.mammasrecipe.room.Recipe

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var dataList: ArrayList<Recipe?>
    private var recipe: List<Recipe?>? = null

    @SuppressLint("ServiceCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        binding.etSearch.requestFocus()
        backClick()
        var db = Room.databaseBuilder(this@SearchActivity, AppDatabase::class.java, "db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()
        var daoObject=db.getDao()
        recipe = daoObject.getAll()
        setUpRecyclerView()
        binding.etSearch.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(s.toString() != ""){
                    filterData(s.toString())
                } else {
                    setUpRecyclerView()
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })
        val imm = getSystemService(INPUT_METHOD_SERVICE) as? InputMethodService

        binding.rvSearch.setOnClickListener {
            imm?.hideWindow()
        }
    }

    private fun backClick() {
        binding.backSearch.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
            finish()
        }
    }

    private fun filterData(text: String) {
        var filterList= ArrayList<Recipe?>()
        for(i in recipe?.indices!!){
            if(recipe!![i]?.tittle?.lowercase()?.contains(text.lowercase()) == true){
                filterList.add(recipe!![i])
            }
        }
        searchAdapter.filterList(filterList)
    }

    fun setUpRecyclerView(){
        dataList = ArrayList()
        for(i in recipe!!.indices){
            if(recipe!![i]?.category == "Popular")
                dataList.add(recipe!![i]!!)
        }
        searchAdapter = SearchAdapter(this, dataList)
        binding.rvSearch.layoutManager = LinearLayoutManager(this)
        binding.rvSearch.adapter = searchAdapter
    }
}