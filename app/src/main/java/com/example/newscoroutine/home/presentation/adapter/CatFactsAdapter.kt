package com.example.newscoroutine.home.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newscoroutine.R
import com.example.newscoroutine.home.presentation.CatsFactItemViewsState

class CatFactsAdapter : RecyclerView.Adapter<CatFactsAdapter.CatFactsViewHolder>() {

    private val catsList = mutableListOf<CatsFactItemViewsState>()

    private var onItemTapped: ((CatsFactItemViewsState) -> Unit)? = null

    fun setCatsList(catsList: List<CatsFactItemViewsState>) {
        this.catsList.clear()
        this.catsList.addAll(catsList)
    }

    fun onItemTapped(tapped: ((CatsFactItemViewsState) -> Unit)) {
        onItemTapped = tapped
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatFactsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.layout_card_cat_facts,
            parent,
            false
        )
        return CatFactsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatFactsViewHolder, position: Int) {
        val catsFact = catsList[position]
        holder.bind(catsFact)

        holder.itemView.setOnClickListener {
            onItemTapped?.invoke(catsFact)
        }
    }

    override fun getItemCount() = catsList.size

    inner class CatFactsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title = itemView.findViewById<TextView>(R.id.tv_title)
        private val description: TextView = itemView.findViewById(R.id.tv_des)

        fun bind(catsFact: CatsFactItemViewsState) {
            title.text = catsFact.title
            description.text = catsFact.description
        }
    }

}