package com.example.registerzvent.database
import androidx.room.*
data class GuestWithRole(
    @Embedded
    val guest: Guest,

    @ColumnInfo(name = "role_name")
    val nombreRol: String,

    @ColumnInfo(name = "order_of_role")
    val roleOrder: Int
){
    override fun toString(): String {
        return "$guest\nRol: $nombreRol"
    }
}