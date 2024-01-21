package com.example.mammasrecipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mammasrecipe.databinding.ActivityRecipeBinding

class RecipeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecipeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}