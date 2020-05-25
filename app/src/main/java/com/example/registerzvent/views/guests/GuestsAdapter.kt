package com.example.registerzvent.views.guests

import androidx.recyclerview.widget.ListAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.registerzvent.database.GuestWithRole
import com.example.registerzvent.databinding.ListItemGuestBinding

class GuestsAdapter: ListAdapter<GuestWithRole, GuestsAdapter.ViewHolder> (GuestsDiffCallback()){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }





    class ViewHolder private constructor(val binding: ListItemGuestBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: GuestWithRole){
            binding.guest = item
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup):ViewHolder{
                val layoutInflater =
                    LayoutInflater.from(parent.context)
                val binding = ListItemGuestBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(binding)
            }
        }

    }

    class GuestsDiffCallback: DiffUtil.ItemCallback<GuestWithRole>(){
        override fun areItemsTheSame(oldItem: GuestWithRole, newItem: GuestWithRole): Boolean {
            return oldItem.guest.eventGuestsId == newItem.guest.eventGuestsId
        }

        override fun areContentsTheSame(oldItem: GuestWithRole, newItem: GuestWithRole): Boolean {
            return oldItem == newItem
        }

    }
}