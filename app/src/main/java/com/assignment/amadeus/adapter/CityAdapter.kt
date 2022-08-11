package com.assignment.amadeus.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.assignment.amadeus.data.local.CityEntity
import com.assignment.amadeus.databinding.CityRowBinding
import com.assignment.amadeus.ui.DetailsPage


class CityAdapter(private val onClickListener: OnClickListener) : ListAdapter<CityEntity, CityAdapter.MyHolder>(DiffutilCallBack) {

    object DiffutilCallBack : DiffUtil.ItemCallback<CityEntity>() {
        override fun areItemsTheSame(oldItem: CityEntity, newItem: CityEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CityEntity, newItem: CityEntity): Boolean {
            return oldItem.id == newItem.id
        }

    }

    inner class MyHolder(private val binding: CityRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val context: Context? = null

        fun bind(post: CityEntity?) {
            binding.cityNameTv.text = post?.cityName +", " + post?.country
            binding.countryNameTv.text = post?.temp.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(CityRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(post)
        }
    }
    class OnClickListener(val clickListener: (cityEntity: CityEntity) -> Unit) {
        fun onClick(cityEntity: CityEntity) = clickListener(cityEntity)
    }
}