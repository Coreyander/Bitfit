package com.example.codepath_project_5_bitfit.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.PopupMenu
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.codepath_project_5_bitfit.BitFitViewModel
import com.example.codepath_project_5_bitfit.BitfitApplication
import com.example.codepath_project_5_bitfit.R
import com.example.codepath_project_5_bitfit.activities.MenuActivity
import com.example.codepath_project_5_bitfit.adapters.MyMetricsRecyclerViewAdapter
import com.example.codepath_project_5_bitfit.database.AppDatabase
import com.example.codepath_project_5_bitfit.database.FitnessEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MyMetricsFragment : Fragment(), MyMetricsRecyclerViewAdapter.ClickListener {

    private lateinit var adapter: MyMetricsRecyclerViewAdapter
    private val viewModel: BitFitViewModel by activityViewModels()
    private lateinit var entries: LiveData<List<FitnessEntity>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        CoroutineScope(IO).launch {
            val db = AppDatabase.getInstance(this@MyMetricsFragment.requireContext())
            entries = db.fitnessDao().getAll()
        }
        val view = inflater.inflate(R.layout.fragment_my_metrics, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.myMetricsRecyclerView)
        adapter = MyMetricsRecyclerViewAdapter(requireContext(), entries, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        entries.observe(viewLifecycleOwner) { recyclerView.adapter?.notifyDataSetChanged() }
        return view
    }

    override fun onEntryClick(position: Int) {
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
            ((activity as MenuActivity).application as BitfitApplication).db.fitnessDao()
                .deleteThis(entries.value?.get(position))
        }
        adapter.notifyDataSetChanged()
    }

}