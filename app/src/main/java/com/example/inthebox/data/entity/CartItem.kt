package com.example.inthebox.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "cart_items")
data class CartItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val price: Double,
    val height: Int,
    val width: Int,
    val length: Int,
    val quantity: Int = 1,
    val imageUrl: String = "" //
) : Serializable {
    val volume: Int
        get() = height * width * length

    fun formattedPrice(): String = "R$ %.2f".format(price)

    fun dimensions(): String = "H: $height x W: $width x L: $length"
}