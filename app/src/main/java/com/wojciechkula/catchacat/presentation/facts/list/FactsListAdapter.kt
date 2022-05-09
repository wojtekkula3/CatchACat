package com.wojciechkula.catchacat.presentation.facts.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.wojciechkula.catchacat.R

class FactsListAdapter(
    private val onItemClicked: (fact: FactItem) -> Unit
) : ListAdapter<FactItem, FactsViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fact_item, parent, false)
        return FactsViewHolder(view)
    }

    override fun onBindViewHolder(holder: FactsViewHolder, position: Int) {
        holder.bind(getItem(position), position, onItemClicked)
    }
}

class DiffCallback : DiffUtil.ItemCallback<FactItem>() {
    override fun areItemsTheSame(oldItem: FactItem, newItem: FactItem): Boolean {
        return oldItem._id == newItem._id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: FactItem, newItem: FactItem): Boolean {
        return oldItem == newItem
    }
}