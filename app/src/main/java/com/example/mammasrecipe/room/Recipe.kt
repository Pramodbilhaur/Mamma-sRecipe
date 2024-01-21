package com.example.mammasrecipe.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity("recipe")
data class Recipe (
    var img: String,
    var tittle: String,
    var des: String,
    var ing: String,
    var category: String,

    @JvmField
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
    ): Serializable