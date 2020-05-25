package com.example.registerzvent.database

import androidx.room.*
import androidx.room.ForeignKey.SET_DEFAULT
import androidx.room.ForeignKey.SET_NULL

//Jose Hurtarte 19707
//clase de modelo para implementar databinding y a todos los registrados

@Entity(tableName ="event_guests_table",
    foreignKeys = [ForeignKey(
        entity = Roles::class,
        parentColumns = ["eventRolesId"],
        childColumns = ["role_of_guestID"],
        onDelete = SET_NULL)
    ])

data class Guest (
    @PrimaryKey(autoGenerate = true)
    var eventGuestsId: Long = 0L,

    @ColumnInfo(name ="guest_name")
    var name:String = "",

    @ColumnInfo(name = "guest_phone")
    var phone:String = "",

    @ColumnInfo(name = "guest_email")
    var email:String = "",

    @ColumnInfo(name = "guest_registered_status")
    var registered:String = "No",

    @ColumnInfo(name = "role_of_guestID")
    var roleOfGuestId:Long= 0L){


    override fun toString(): String {
        return "Invitado No.$eventGuestsId\nNombre: $name\nTelefono: $phone\nEmail: $email\nRegistrado: $registered"
    }
}