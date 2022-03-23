package com.example.moviesapp.database

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Items::class], version = 2)
abstract class RoomAppDb : RoomDatabase() {

    abstract fun userDao(): roomDao

    companion object {
        @Volatile
        private var INSTANCE: RoomAppDb? = null
        val migration_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
            }
        }

        fun getAppDatabase(context: Context): RoomAppDb {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder<RoomAppDb>(
                        context.applicationContext, RoomAppDb::class.java, "AppDB3"
                    )
                        .addMigrations(migration_1_2)
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}