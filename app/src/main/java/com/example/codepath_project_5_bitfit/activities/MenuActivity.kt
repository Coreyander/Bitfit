package com.example.codepath_project_5_bitfit.activities

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.Resource
import com.example.codepath_project_5_bitfit.R
import com.example.codepath_project_5_bitfit.fragments.*
import com.example.codepath_project_5_bitfit.BitfitApplication
import com.example.codepath_project_5_bitfit.BitFitViewModel
import com.example.codepath_project_5_bitfit.adapters.MyMetricsRecyclerViewAdapter
import com.example.codepath_project_5_bitfit.database.FitnessEntity
import com.google.android.material.bottomnavigation.BottomNavigationView
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
        val nav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val fab = findViewById<FloatingActionButton>(R.id.fab)


        /** ViewModel **/
        val viewModel : BitFitViewModel by viewModels()
        var mood : String? = null
        var sleep : Float? = 0.0f
        var food : String? = null
        var calories : Int? = 0
        var water : Float? = 0.0f
        viewModel.mood.observe(this) { mood = viewModel.mood.value }
        viewModel.water.observe(this) {water = viewModel.water.value}
        viewModel.sleep.observe(this) {sleep = viewModel.sleep.value}
        viewModel.calories.observe(this) {calories = viewModel.calories.value}
        //viewModel.

        /**Click Listeners **/
        //Note: the fab deals specifically with new entries right now. Perhaps move to NewEntriesFragment?
        /** Nav bar navigation. Replaces Frame element in activity with chosen fragment **/
        nav.setOnItemSelectedListener{ item ->
            when(item.itemId) {
                (R.id.action_log) -> {
                        fab.visibility = View.VISIBLE
                        val supportFragmentManager = supportFragmentManager
                        supportFragmentManager.commit {
                            setCustomAnimations(
                                R.anim.fade_in,
                                R.anim.slide_out
                            )
                            replace(R.id.content, NewEntryFragment())
                            addToBackStack(null)
                        }
                        true

                    }

                (R.id.action_my_metrics) -> {
                    fab.visibility = View.INVISIBLE
                    val supportFragmentManager = supportFragmentManager
                    supportFragmentManager.commit {
                        setCustomAnimations(
                            R.anim.fade_in,
                            R.anim.slide_out
                        )
                        replace(R.id.content, MyMetricsFragment())
                        addToBackStack(null)
                    }
                    true
                }
                (R.id.action_stats) -> {
                    fab.visibility = View.INVISIBLE
                    //TODO: Finish stats fragment
                    true
                }
                else ->
                    true
            }
            //TODO: Implement interface for saving a full entry!
        }
        fab.setOnClickListener {
            createEntry(mood, sleep, food, calories, water)
        }
    }

    //The app now uses LiveData!
    //Create entry is calling to add an entry which triggers the observer which then notifies the adapter
    private fun createEntry(mood : String?, sleep: Float?, food : String?, calories : Int?, water : Float?) {
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