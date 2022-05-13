package com.wojciechkula.catchacat.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Facts")
data class FactEntity(
    @PrimaryKey
    val _id: String = "",
    val text: String = "",
    val createdAt: Date = Date(),
    val updatedAt: Date = Date(),
)