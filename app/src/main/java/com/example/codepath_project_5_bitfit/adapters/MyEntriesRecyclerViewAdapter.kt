package com.example.codepath_project_5_bitfit.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.codepath_project_5_bitfit.R
import com.example.codepath_project_5_bitfit.database.FitnessEntity

class MyEntriesRecyclerViewAdapter(private val context: Context,
                                   private val entries: List<FitnessEntity>?,
                                   private val listener: ItemClickListener)
    : RecyclerView.Adapter<MyEntriesRecyclerViewAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.viewholder_entries, parent,false)
        return ViewHolder(view)
    }

    interface ItemClickListener {
        fun onItemLongClick(position: Int)
        fun onItemClick(position: Int)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnLongClickListener, View.OnClickListener {
        val moodStatTextView : TextView = itemView.findViewById(R.id.moodStatTextView)
        val waterStatTextView : TextView = itemView.findViewById(R.id.waterStatTextView)
        val nutritionStatTextView : TextView = itemView.findViewById(R.id.nutritionStatTextView)
        val sleepStatTextView : TextView = itemView.findViewById(R.id.sleepStatTextView)
        val dateTextView : TextView = itemView.findViewById(R.id.dateTextView)
        init {
            itemView.setOnLongClickListener(this)
            itemView.setOnClickListener(this)
        }
        override fun onLongClick(v: View?): Boolean {
            val position : Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemLongClick(position)
            }
            return true
        }

        override fun onClick(v: View?) {
            val position : Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    override fun onBindViewHolder(holder: MyEntriesRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.itemView.setOnLongClickListener{ true }
        holder.dateTextView.text = entries?.get(position)?.date
        holder.dateTextView.setOnLongClickListener{ true }
        holder.sleepStatTextView.text = entries?.get(position)?.sleep
        holder.nutritionStatTextView.text = entries?.get(position)?.calories
        holder.waterStatTextView.text = entries?.get(position)?.water
        holder.moodStatTextView.text = entries?.get(position)?.mood

    }

    override fun getItemCount(): Int {
        return entries?.size ?: 0
    }
}