package com.wojciechkula.catchacat.presentation.facts.list

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
class FactItem(
    val _id: String,
    val text: String,
    val updatedAt: Date,
) : Parcelable