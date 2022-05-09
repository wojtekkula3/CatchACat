package com.wojciechkula.catchacat.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Facts")
data class FactEntity(
    @PrimaryKey
    val _id: String = "",
    val text: String = "",
    val createdAt: String = "",
    val updatedAt: String = "",
)