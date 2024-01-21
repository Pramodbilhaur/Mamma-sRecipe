package com.example.mammasrecipe

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import coil.load
import com.example.mammasrecipe.databinding.ActivityRecipeBinding
import com.example.mammasrecipe.room.Recipe

class RecipeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecipeBinding
    var isCrop = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recipe = intent.getSerializableExtra("RECIPE") as Recipe
        println("recipe is $recipe")

        binding.btnResize.setOnClickListener {
            if(isCrop){
                binding.mainImage.scaleType = ImageView.ScaleType.FIT_CENTER
                binding.btnResize.setColorFilter(ContextCompat.getColor(this, R.color.black), PorterDuff.Mode.SRC_IN)
                isCrop = !isCrop
            } else {
                binding.mainImage.scaleType = ImageView.ScaleType.CENTER_CROP
                binding.btnResize.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_IN)
                isCrop = !isCrop
            }
        }

        binding.tvTitle.text = recipe.tittle
        var time = recipe.ing.split("\n".toRegex()).dropLastWhile {
            it.isEmpty()
        }.toTypedArray()
        binding.tvDuration.text = time[0]

        binding.ingData.text = time.drop(1)
            .joinToString(separator = "\n") { "🟢 $it" }

        Toast.makeText(this, time[0], Toast.LENGTH_SHORT).show()
        binding.mainImage.load(recipe.img){
            placeholder(R.drawable.ic_launcher_background)
        }


//        binding.ingData.text = recipe.ing

        binding.btnSteps.background = null
        binding.btnSteps.setTextColor(getColor(R.color.black))

        binding.btnSteps.setOnClickListener {
            binding.btnIngredients.setTextColor(getColor(R.color.black))
            binding.btnIngredients.background = null
            binding.btnSteps.background = getDrawable(R.drawable.btn_ing)
            binding.btnSteps.setTextColor(getColor(R.color.white))
            binding.svIng.visibility = View.GONE
            binding.svSteps.visibility = View.VISIBLE
            binding.stepsData.text = recipe.des

        }

        binding.btnIngredients.setOnClickListener {
            binding.btnSteps.setTextColor(getColor(R.color.black))
            binding.btnSteps.background = null
            binding.btnIngredients.background = getDrawable(R.drawable.btn_ing)
            binding.btnIngredients.setTextColor(getColor(R.color.white))
            binding.svSteps.visibility = View.GONE
            binding.svIng.visibility = View.VISIBLE
            binding.ingData.text = time.drop(1)
                .joinToString(separator = "\n") { "🟢 $it" }
        }

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
            finish()
        }

    }
}