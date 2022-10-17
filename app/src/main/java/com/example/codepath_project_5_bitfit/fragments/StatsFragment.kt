package com.example.codepath_project_5_bitfit.fragments

import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.codepath_project_5_bitfit.R
import com.example.codepath_project_5_bitfit.database.AppDatabase
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


class StatsFragment : Fragment() {

lateinit var moodChart: BarChart
lateinit var moodData : BarData
lateinit var moodDataSet: BarDataSet
lateinit var barEntriesList: ArrayList<BarEntry>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_stats, container, false)

        /** Mood Chart initialization **/
        moodChart = view.findViewById(R.id.moodChart)
        getBarChartData()
        moodDataSet = BarDataSet(barEntriesList, "Mood Chart Data")
        moodData = BarData(moodDataSet)
        moodChart.data = moodData

        /** Mood Chart customization **/
        moodDataSet.valueTextColor = Color.BLACK
        //moodDataSet.color
        moodDataSet.valueTextSize = 16f
        moodChart.description.isEnabled = false

        return view
    }

    private fun getBarChartData() {
        lifecycleScope.launch(IO) {
            val db = AppDatabase.getInstance(this@StatsFragment.requireContext())
            val moods: MutableList<String?> = db.fitnessDao().getMood()
        }
    }
}