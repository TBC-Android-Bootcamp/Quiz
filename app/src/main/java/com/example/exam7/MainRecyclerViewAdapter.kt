package com.example.exam7

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_recycler_layout.view.*

class MainRecyclerViewAdapter( private val items: Array<Array<ModelClass>>) :
    RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_recycler_layout,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = items.size


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind() {
            // onBind-ს გადავცეთ "არამთავარი" რესაიქლერვიუ და მისი ადაპტერი.
            itemView.secondRecyclerView.layoutManager = LinearLayoutManager(itemView.context)
            val childAdapter = SecondaryRecyclerViewAdapter(items[adapterPosition])
            itemView.secondRecyclerView.adapter = childAdapter
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind()
    }
}