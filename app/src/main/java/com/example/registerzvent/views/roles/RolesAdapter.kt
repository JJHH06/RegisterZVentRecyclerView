package com.example.registerzvent.views.roles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.registerzvent.R
import com.example.registerzvent.database.Roles

class RolesAdapter:RecyclerView.Adapter<RolesAdapter.ViewHolder>() {
    var data = listOf<Roles>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }



    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val roleName: TextView = itemView.findViewById(R.id.role_name)
        val descriptionString: TextView = itemView.findViewById(R.id.description_string)
        val priorityImage: ImageView = itemView.findViewById(R.id.priority_image)

         fun bind( item: Roles) {
            val res = itemView.context.resources
            roleName.text = item.nombre
            descriptionString.text = item.description
            priorityImage.setImageResource(
                when (item.rolesOrder) {
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
                }
            )
        }
        companion object {
             fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater =
                    LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(
                        R.layout.list_item_roles,
                        parent, false
                    )
                return ViewHolder(view)
            }
        }
    }



}
