package com.example.exam7

import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.transition.Transition
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import kotlinx.android.synthetic.main.chooser_layout.view.*
import kotlinx.android.synthetic.main.input_layout.view.*

class SecondaryRecyclerViewAdapter(val items: Array<ModelClass>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val INPUT = 1
        const val CHOOSER = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == INPUT) {
            InputViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.input_layout, parent, false)
            )
        } else {
            ChooserViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.chooser_layout, parent, false)
            )
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is InputViewHolder) {
            holder.onBind()
        } else if (holder is ChooserViewHolder) {
            holder.onBind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = items[position]
        if (item.field_type == "input")
            return INPUT
        return CHOOSER

    }

    inner class InputViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var item: ModelClass

        fun onBind() {
            item = items[adapterPosition]
            itemView.etItem.id = item.field_id


            val editText = itemView.findViewById<EditText>(item.field_id)

            if (!item.is_active) {
                editText.visibility = View.GONE
            }

            editText.hint = item.hint
            editText.inputType = InputType.TYPE_CLASS_TEXT

            Glide.with(itemView.context)
                .load(item.icon)
                .centerCrop()
                .into(object : CustomTarget<Drawable>() {
                    override fun onLoadCleared(placeholder: Drawable?) {
                    }

                    override fun onResourceReady(
                        resource: Drawable,
                        transition: com.bumptech.glide.request.transition.Transition<in Drawable>?
                    ) {
                        editText.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            null,
                            null,
                            resource,
                            null
                        )
                    }

                })
        }
    }

    inner class ChooserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var item: ModelClass

        fun onBind() {
            item = items[adapterPosition]
            itemView.btnChooser.text = item.hint
        }
    }
}