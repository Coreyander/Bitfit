package com.example.codepath_project_5_bitfit.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.codepath_project_5_bitfit.R
import com.example.codepath_project_5_bitfit.database.FitnessEntity

class MyMetricsRecyclerViewAdapter(private val context: Context,
                                   private val entries: LiveData<List<FitnessEntity>>,
                                   private val listener: ClickListener)
    :RecyclerView.Adapter<MyMetricsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.viewholder_my_metrics, parent,false)
        return ViewHolder(view)
    }

    interface ClickListener {
        fun onEntryClick(position: Int)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        val moodStatTextView: TextView = itemView.findViewById(R.id.moodStatTextView)
        val sleepStatTextView: TextView = itemView.findViewById(R.id.sleepStatTextView)
        val waterStatTextView: TextView = itemView.findViewById(R.id.waterStatTextView)
        val nutritionStatTextView: TextView = itemView.findViewById(R.id.nutritionStatTextView)
        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            val position : Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onEntryClick(position)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dateTextView.text = entries.value?.get(position)?.date.toString()
        holder.moodStatTextView.text = entries.value?.get(position)?.mood
        holder.sleepStatTextView.text = entries.value?.get(position)?.sleep.toString() + " Hours"
        holder.waterStatTextView.text = entries.value?.get(position)?.water.toString() + " Cups"
        holder.nutritionStatTextView.text = entries.value?.get(position)?.calories.toString() + " Calories"
    }

    override fun getItemCount(): Int {
        return entries.value?.size ?: 0
    }

}