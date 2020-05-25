package com.example.registerzvent.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface GuestRoleDatabaseDao {
    @Insert
    fun insertRole(role: Roles)

    @Update
    fun updateRole(role: Roles)

    @Query("SELECT * from event_roles_table WHERE eventRolesId = :key")
    fun getRole(key: Long): Roles?

    @Query("DELETE FROM event_roles_table")
    fun clearRoles()

    @Query("SELECT * FROM event_roles_table ORDER BY eventRolesId DESC LIMIT 1")
    fun getLastRole(): Roles?

    @Query("SELECT * FROM event_roles_table ORDER BY order_of_role DESC")
    fun getAllRoles(): LiveData<List<Roles>>


    @Insert
    fun insertGuest(guest:Guest)

    @Update
    fun updateGuest(guest: Guest)

    @Query("SELECT * from event_guests_table WHERE eventGuestsId = :key")
    fun getGuest(key: Long): Guest?

    @Query("SELECT g.*,r.role_name, r.order_of_role FROM event_guests_table as g LEFT JOIN event_roles_table as r ON g.role_of_guestID =r.eventRolesId ORDER BY order_of_role")
    fun getAllGuests(): LiveData<List<GuestWithRole>>

    @Query("SELECT g.*,r.role_name, r.order_of_role FROM event_guests_table as g LEFT JOIN event_roles_table as r ON g.role_of_guestID =r.eventRolesId ORDER BY order_of_role DESC")
    fun getAllGuestsNormalList(): List<GuestWithRole>


}
