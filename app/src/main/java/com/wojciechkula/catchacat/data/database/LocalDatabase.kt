package com.wojciechkula.catchacat.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wojciechkula.catchacat.data.dao.FactDao
import com.wojciechkula.catchacat.data.entity.FactEntity

@Database(entities = [FactEntity::class], version = 1)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun factsDao(): FactDao

    companion object {
        @Volatile
        private var INSTANCE: LocalDatabase? = null

        fun getDatabase(context: Context): LocalDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance =
                    Room.databaseBuilder(context.applicationContext, LocalDatabase::class.java, "catch_a_cat_database")
                        .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}