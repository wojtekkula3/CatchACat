package com.wojciechkula.catchacat.domain.model

import java.util.*

data class FactModel(
    val _id: String,
    val text: String,
    val createdAt: Date,
    val updatedAt: Date,
)