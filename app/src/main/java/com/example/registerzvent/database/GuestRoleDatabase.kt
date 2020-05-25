package com.example.registerzvent.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Guest::class,Roles::class], version = 1, exportSchema = false)
abstract class GuestRoleDatabase : RoomDatabase() {

    abstract val guestRoleDatabaseDao: GuestRoleDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: GuestRoleDatabase? = null

        fun getInstance(context: Context): GuestRoleDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        GuestRoleDatabase::class.java,
                        "sleep_history_database" //No se porque pero la unica forma en la que no dio error fue con el nombre del codelab
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}