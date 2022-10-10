package com.example.codepath_project_5_bitfit.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.codepath_project_5_bitfit.BitfitApplication
import com.example.codepath_project_5_bitfit.R
import com.example.codepath_project_5_bitfit.activities.MenuActivity
import com.example.codepath_project_5_bitfit.adapters.MyEntriesRecyclerViewAdapter
import com.example.codepath_project_5_bitfit.database.AppDatabase
import com.example.codepath_project_5_bitfit.database.FitnessEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield

class MyEntriesFragment() : Fragment(), MyEntriesRecyclerViewAdapter.ItemClickListener {

    private val viewModel: FragmentViewModel by activityViewModels()
    private lateinit var adapter: MyEntriesRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_entries, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.myEntriesRecyclerView)
        val appData = AppDatabase.getInstance(this.requireContext())
        lifecycleScope.launch {
            val entries = appData.fitnessDao().getAll()
            viewModel.setData(entries)
        }
        adapter = MyEntriesRecyclerViewAdapter(this.requireContext(), viewModel.entries, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        val noEntriesTextView = view.findViewById<TextView>(R.id.noEntriesTextView)
      //  if(viewModel.entries.isEmpty()) {
      //      noEntriesTextView.visibility = View.VISIBLE
      //  }
      //  else
      //      noEntriesTextView.visibility = View.GONE
        return view
    }

    //Debug: Can't get this to trigger?
    override fun onItemLongClick(position: Int) {
        deleteEntry(position)
    }

    override fun onItemClick(position: Int) {
       val pop = PopupMenu(this.requireContext(), view)
       pop.menuInflater.inflate(R.menu.popup_menu, pop.menu)
        pop.setOnMenuItemClickListener { item ->
            when(item.itemId) {
                R.id.delete ->
                    deleteEntry(position)
            }
            true
        }
        pop.show()
    }

    private fun deleteEntry(position: Int) {
        CoroutineScope(IO).launch {
            ((activity as MenuActivity).application as BitfitApplication).db.fitnessDao().deleteThis(viewModel.entries[position])
        }
        adapter.notifyDataSetChanged()

    }

}