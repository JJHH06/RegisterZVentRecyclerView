package com.example.registerzvent.views

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.registerzvent.R
import com.example.registerzvent.database.GuestWithRole
import com.example.registerzvent.database.Roles

@BindingAdapter("roleName")
fun TextView.setRoleName(item: Roles){
    text = item.nombre
}
@BindingAdapter("roleDescription")
fun TextView.setRoleDescription(item: Roles){
    text = item.description
}

@BindingAdapter("roleOrderImage")
fun ImageView.setRoleNameFormatted(item: Roles){
    setImageResource( when (item.rolesOrder) {
        1 -> R.drawable.priority_1
        2 -> R.drawable.priority_2
        3 -> R.drawable.priority_3
        4 -> R.drawable.priority_4
        5 -> R.drawable.priority_5
        6 -> R.drawable.priority_6
        7 -> R.drawable.priority_7
        8 -> R.drawable.priority_8
        9 -> R.drawable.priority_9
        10 -> R.drawable.priority_10
        else -> R.drawable.priority_1
    })
}


//Desde aqui ya es para los invitados

@BindingAdapter("isRegisteredImage")
fun ImageView.setRegisteredStatusImage(item: GuestWithRole){
    setImageResource( when (item.guest.registered) {
        "No" -> R.drawable.confirmation_red_xmark
        "Si" -> R.drawable.confirmation_green_check
        else -> R.drawable.confirmation_red_xmark
    })
}

@BindingAdapter("guestPriorityImage")
fun ImageView.setGuestPriorityImage(item: GuestWithRole){
    setImageResource( when (item.roleOrder) {
        1 -> R.drawable.priority_1
        2 -> R.drawable.priority_2
        3 -> R.drawable.priority_3
        4 -> R.drawable.priority_4
        5 -> R.drawable.priority_5
        6 -> R.drawable.priority_6
        7 -> R.drawable.priority_7
        8 -> R.drawable.priority_8
        9 -> R.drawable.priority_9
        10 -> R.drawable.priority_10
        else -> R.drawable.priority_1
    })
}

@SuppressLint("SetTextI18n")
@BindingAdapter("guestFormatName")
fun TextView.setGuestFormatName(item: GuestWithRole){
    text = "${item.guest.name}  (${item.nombreRol})"
}

@BindingAdapter("guestEmail")
fun TextView.setGuestEmail(item: GuestWithRole){
    text = item.guest.email
}

@BindingAdapter("guestPhone")
fun TextView.setGuestPhone(item: GuestWithRole){
    text = item.guest.phone
}