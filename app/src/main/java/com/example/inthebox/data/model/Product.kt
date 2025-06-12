package com.example.inthebox.data.model

import java.io.Serializable

data class Product(
    val name: String,
    val price: Double,
    val height: Int,
    val width: Int,
    val length: Int,
    val imageUrls: List<String> = emptyList()
) : Serializable