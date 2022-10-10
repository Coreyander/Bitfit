package com.example.codepath_project_5_bitfit.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.codepath_project_5_bitfit.R

class MenuRecyclerViewAdapter(private val context: Context, private val iconIds: List<Int>, private val listener: ItemClickListener
) : RecyclerView.Adapter<MenuRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.viewholder_menu, parent, false)
        return ViewHolder(view)
    }

    interface ItemClickListener {
        fun onItemClicked(position: Int)
    }

    //what each icon has
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val menuTextView: TextView = itemView.findViewById(R.id.menuTextView)
        val entryButton: ImageButton = itemView.findViewById(R.id.menuImageButton)
        val moodButton: ImageButton = itemView.findViewById(R.id.menuImageButton)
        val waterButton: ImageButton = itemView.findViewById(R.id.menuImageButton)
        val nutritionButton: ImageButton = itemView.findViewById(R.id.menuImageButton)
        val sleepButton: ImageButton = itemView.findViewById(R.id.menuImageButton)

    }

    //what each icon will get
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            when (position) {
                0 -> {
                    holder.menuTextView.text = context.getString(R.string.my_entries)
                    holder.entryButton.setBackgroundResource(R.drawable.entry_icon)
                    holder.entryButton.setOnClickListener { listener.onItemClicked(position) }
                }
                1 -> {
                    holder.menuTextView.text = context.getString(R.string.select_mood)
                    holder.moodButton.setBackgroundResource(R.drawable.mood_icon)
                    holder.moodButton.setOnClickListener { listener.onItemClicked(position) }
                }
                2 -> {
                    holder.waterButton.setBackgroundResource(R.drawable.water_icon)
                    holder.waterButton.setOnClickListener { listener.onItemClicked(position) }
                }
                3 -> {
                    holder.nutritionButton.setBackgroundResource(R.drawable.nutrition_icon)
                    holder.nutritionButton.setOnClickListener { listener.onItemClicked(position) }
                }
                4 -> {
                    holder.sleepButton.setBackgroundResource(R.drawable.sleep_icon)
                    holder.sleepButton.setOnClickListener { listener.onItemClicked(position) }
                }
            }
    }

    //how many icons in the list should be loaded
    override fun getItemCount(): Int {
        return iconIds.size
    }

}