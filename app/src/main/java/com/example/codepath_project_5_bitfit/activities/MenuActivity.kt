package com.example.codepath_project_5_bitfit.activities

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.codepath_project_5_bitfit.R
import com.example.codepath_project_5_bitfit.adapters.MenuRecyclerViewAdapter
import com.example.codepath_project_5_bitfit.fragments.*
import com.example.codepath_project_5_bitfit.BitfitApplication
import com.example.codepath_project_5_bitfit.BitFitViewModel
import com.example.codepath_project_5_bitfit.ViewModelFactory
import com.example.codepath_project_5_bitfit.database.FitnessEntity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.Date

class MenuActivity: AppCompatActivity() {

    private lateinit var menuRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val save = findViewById<FloatingActionButton>(R.id.addBtn)
        val openMenu = findViewById<ImageButton>(R.id.openMenuBtn)

        //ViewModel
        val viewModel : BitFitViewModel by viewModels {ViewModelFactory((application as BitfitApplication).repo)}
        var mood : String? = null
        var sleep : String? = null
        var food : String? = null
        var calories : String? = null
        var water : String? = null
        viewModel.mood.observe(this) { mood = viewModel.mood.value }
        viewModel.entries.observe(this) { menuRecyclerView.adapter?.notifyDataSetChanged() }
        menuRecyclerView = findViewById(R.id.recyclerView)
        val iconIds = listOf(0, 1, 2, 3, 4)

        //RecyclerView Adapter + click listener for adapter
        val menuAdapter = MenuRecyclerViewAdapter(this, iconIds, object: MenuRecyclerViewAdapter.ItemClickListener {
            override fun onItemClicked(position: Int) {
                executeClick(position)
            }
        })

        menuRecyclerView.adapter = menuAdapter
        menuRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

        //Save entry click listener
        save.setOnClickListener{
            createEntry(mood, sleep, food, calories, water)
        }
        //Open menu click listener
        openMenu.setOnClickListener{
            if(menuRecyclerView.visibility == View.GONE)
                menuRecyclerView.visibility = View.VISIBLE
            else if(menuRecyclerView.visibility == View.VISIBLE)
                menuRecyclerView.visibility = View.GONE
        }
    }

    fun executeClick(pos: Int) {
        /**
         0 - entries
         1 - mood
         2 - water
         3 - nutrition
         4 - sleep **/
        when(pos) {
            0 -> {
                val supportFragmentManager = supportFragmentManager
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.content, MyEntriesFragment(), null).commit()
            }
            1 -> {
                val supportFragmentManager = supportFragmentManager
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.content, MoodFragment(), null).commit()
            }
            2 -> {
                val supportFragmentManager = supportFragmentManager
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.content, WaterFragment(), null).commit()
            }
            3 -> {
                val supportFragmentManager = supportFragmentManager
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.content, NutritionFragment(), null).commit()
            }
            4 -> {
                val supportFragmentManager = supportFragmentManager
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.content, SleepFragment(), null).commit()
            }
        }
    }

    //The app now uses LiveData!
    //Create entry is calling to add an entry which triggers the observer which then notifies the adapter
    private fun createEntry(mood : String?, sleep: String?, food : String?, calories : String?, water : String?) {
        CoroutineScope(Dispatchers.IO).launch {
            (application as BitfitApplication).db.fitnessDao().insert(
                FitnessEntity(
                    id = 0,
                    name = "Robin",
                    date = Date(System.currentTimeMillis()).toString(),
                    mood = mood,
                    sleep = sleep,
                    sleep_goal = null,
                    water = water,
                    waterGoal = null,
                    food = food,
                    calories = calories,
                    foodGoal = null
                )
            )
        }
    }
}