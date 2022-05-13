package com.wojciechkula.catchacat.presentation.facts.list

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.wojciechkula.catchacat.R

class FactsViewHolder(
    private val view: View
) : RecyclerView.ViewHolder(view) {

    fun bind(fact: FactItem, position: Int, onItemClicked: (fact: FactItem) -> Unit) {
        (view.findViewById(R.id.factNumberLabel) as TextView).text = "Fact #${position + 1}"
        (view.findViewById(R.id.factIdLabel) as TextView).text = "ID: ${fact._id}"
        (view.findViewById(R.id.factCardView) as CardView).setOnClickListener { onItemClicked(fact) }
    }
}