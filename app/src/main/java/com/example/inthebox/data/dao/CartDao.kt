package com.example.inthebox.data.dao

import androidx.room.*
import com.example.inthebox.data.entity.CartItem


@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: CartItem)

    @Query("SELECT * FROM cart_items")
    suspend fun getAll(): List<CartItem>

    @Query("DELETE FROM cart_items")
    suspend fun clear()

    @Delete
    suspend fun delete(item: CartItem)

    @Query("SELECT SUM(price * quantity) FROM cart_items")
    suspend fun getTotal(): Double?
}