package com.example.registerzvent.views.roles

import android.view.LayoutInflater
import android.view.ViewGroup
//import android.widget.ListAdapter

import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.registerzvent.database.Roles
import com.example.registerzvent.databinding.ListItemRolesBinding

class RolesAdapter(val clickListener: RolesListener): ListAdapter<Roles, RolesAdapter.ViewHolder> (RolesDiffCallback()){
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(getItem(position)!!, clickListener)

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }



    class ViewHolder private constructor(val binding: ListItemRolesBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(
            item: Roles,
            clickListener: RolesListener
        ) {
            binding.role = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater =
                    LayoutInflater.from(parent.context)
                val binding = ListItemRolesBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(binding)
            }
        }
    }

}


class RolesDiffCallback : DiffUtil.ItemCallback<Roles>(){
    override fun areItemsTheSame(oldItem: Roles, newItem: Roles): Boolean {
        return oldItem.eventRolesId == newItem.eventRolesId
    }

    override fun areContentsTheSame(oldItem: Roles, newItem: Roles): Boolean {
        return oldItem == newItem
    }

}

class RolesListener(val clickListener: (eventRolesId: Long) -> Unit){
    fun onClick(role: Roles) =clickListener(role.eventRolesId)
}