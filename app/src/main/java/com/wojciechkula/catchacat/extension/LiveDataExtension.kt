package com.wojciechkula.catchacat.extension

import androidx.lifecycle.LiveData

@Suppress("UnsafeCallOnNullableType")
fun <T> LiveData<T>.newBuilder(newValue: T.() -> T) = newValue(this.value!!)