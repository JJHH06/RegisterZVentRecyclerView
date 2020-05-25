package com.example.registerzvent.views.guests

import androidx.recyclerview.widget.ListAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.registerzvent.database.GuestWithRole
import com.example.registerzvent.databinding.ListItemGuestBinding

class GuestsAdapter(val clickListener: GuestListener): ListAdapter<GuestWithRole, GuestsAdapter.ViewHolder> (GuestsDiffCallback()){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(getItem(position)!!, clickListener)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }





    class ViewHolder private constructor(val binding: ListItemGuestBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: GuestWithRole, clickListener: GuestListener){
            binding.guest = item
            binding.clickListener = clickListener
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

}

class GuestsDiffCallback: DiffUtil.ItemCallback<GuestWithRole>(){
    override fun areItemsTheSame(oldItem: GuestWithRole, newItem: GuestWithRole): Boolean {
        return oldItem.guest.eventGuestsId == newItem.guest.eventGuestsId
    }

    override fun areContentsTheSame(oldItem: GuestWithRole, newItem: GuestWithRole): Boolean {
        return oldItem == newItem
    }

}

class GuestListener(val clickListener: (eventGuestsId: Long) -> Unit){
    fun onClick(guest: GuestWithRole) = clickListener(guest.guest.eventGuestsId)
}