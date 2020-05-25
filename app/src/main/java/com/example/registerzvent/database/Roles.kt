package com.example.registerzvent.database

import androidx.room.*

@Entity(tableName = "event_roles_table")
data class Roles(
    @PrimaryKey(autoGenerate = true)
    var eventRolesId: Long = 0L,

    @ColumnInfo(name = "role_name")
    var nombre: String ="",

    @ColumnInfo(name = "role_description")
    var description: String ="",

    @ColumnInfo(name = "order_of_role")
    var rolesOrder: Int = 0
){

    override fun toString(): String {
        return nombre
    }
    fun formatRoles(): String{
        return "Rol No.$eventRolesId\nNombre: $nombre\nDescripcion: $description\nPrioridad del rol: $rolesOrder"
    }

}
