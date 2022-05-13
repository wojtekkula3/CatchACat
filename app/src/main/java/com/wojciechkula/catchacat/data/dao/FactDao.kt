package com.wojciechkula.catchacat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wojciechkula.catchacat.data.entity.FactEntity

@Dao
interface FactDao {

    @Query("SELECT * FROM Facts")
    suspend fun getProducts(): List<FactEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<FactEntity>)

    @Query("DELETE FROM Facts")
    suspend fun deleteAll()
}