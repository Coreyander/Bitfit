package com.example.codepath_project_5_bitfit.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.findFragment
import com.example.codepath_project_5_bitfit.BitFitViewModel
import com.example.codepath_project_5_bitfit.R
import com.google.android.material.slider.Slider
import java.text.DecimalFormat
import kotlin.math.roundToInt

class NewEntryFragment : Fragment() {

    private val viewModel: BitFitViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /** The current view is fragment_new_entry **/
        val view = inflater.inflate(R.layout.fragment_new_entry, container, false)

        /** Mood Selection Start **/
        val selection = view.findViewById<TextView>(R.id.selectedMoodTextView)
        val happyBtn = view.findViewById<Button>(R.id.happyBtn)
        val sadBtn = view.findViewById<Button>(R.id.sadBtn)
        val contentBtn = view.findViewById<Button>(R.id.contentBtn)
        val fearBtn = view.findViewById<Button>(R.id.fearBtn)
        val angerBtn = view.findViewById<Button>(R.id.angerBtn)
        val lazyBtn = view.findViewById<Button>(R.id.lazyBtn)
        val motivatedBtn = view.findViewById<Button>(R.id.motivatedBtn)
        val waterSlider = view.findViewById<Slider>(R.id.waterSlider)
        val sleepSlider = view.findViewById<Slider>(R.id.sleepSlider)
        val caloriesEditText = view.findViewById<EditText>(R.id.caloriesEditText)
        val calSaveBtn = view.findViewById<Button>(R.id.caloriesSaveButton)

        viewModel.mood.observe(viewLifecycleOwner) {
            selection?.text = "Current Mood: " + viewModel.mood.value
        }
        happyBtn.setOnClickListener{
            viewModel.setMoodData("Happy")
        }
        sadBtn.setOnClickListener{
            viewModel.setMoodData("Sad")
        }
        contentBtn.setOnClickListener{
            viewModel.setMoodData("Content")
        }
        fearBtn.setOnClickListener{
            viewModel.setMoodData("Fearful")
        }
        angerBtn.setOnClickListener{
            viewModel.setMoodData("Angry")
        }
        lazyBtn.setOnClickListener{
            viewModel.setMoodData("Lazy")
        }
        motivatedBtn.setOnClickListener {
            viewModel.setMoodData("Motivated")
        }
        /** Mood Selection End **/
        /** Water Slider **/
        waterSlider.addOnSliderTouchListener(object: Slider.OnSliderTouchListener{
            override fun onStartTrackingTouch(slider: Slider) {
                val df = DecimalFormat("#.##")
                viewModel.setWaterData(df.format(slider.value).toFloat())
            }

            override fun onStopTrackingTouch(slider: Slider) {
                val df = DecimalFormat("#.##")
                viewModel.setWaterData(df.format(slider.value).toFloat())
            }
        })
        /** Sleep Slider **/
        sleepSlider.addOnSliderTouchListener(object: Slider.OnSliderTouchListener{
            override fun onStartTrackingTouch(slider: Slider) {
                val df = DecimalFormat("#.##")
                viewModel.setSleepData(df.format(slider.value).toFloat())
            }

            override fun onStopTrackingTouch(slider: Slider) {
                val df = DecimalFormat("#.##")
                viewModel.setSleepData(df.format(slider.value).toFloat())
            }
        })
        /** Calories entry **/
        calSaveBtn.setOnClickListener{
            viewModel.setCaloriesData(caloriesEditText.text.toString().toInt())
        }
        return view
    }

}