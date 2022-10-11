package com.example.codepath_project_5_bitfit.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.codepath_project_5_bitfit.R
import com.example.codepath_project_5_bitfit.BitFitViewModel

class MoodFragment() : Fragment() {

    private val viewModel: BitFitViewModel by activityViewModels()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mood, container, false)
        val selection = view.findViewById<TextView>(R.id.selectedMoodTextView)
        val happyBtn = view.findViewById<Button>(R.id.happyBtn)
        viewModel.mood.observe(viewLifecycleOwner) {
            selection?.text = "Current Mood: " + viewModel.mood.value
        }
        happyBtn?.setOnClickListener{
            viewModel.setMoodData("Happy")
        }
        val sadBtn = view.findViewById<Button>(R.id.sadBtn)
        sadBtn?.setOnClickListener{
            viewModel.setMoodData("Sad")
        }
        val contentBtn = view.findViewById<Button>(R.id.contentBtn)
        contentBtn?.setOnClickListener{
            viewModel.setMoodData("Content")
        }
        val fearBtn = view.findViewById<Button>(R.id.fearBtn)
        fearBtn?.setOnClickListener{
            viewModel.setMoodData("Fearful")
        }
        val angerBtn = view.findViewById<Button>(R.id.angerBtn)
        angerBtn?.setOnClickListener{
            viewModel.setMoodData("Angry")
        }
        val lazyBtn = view.findViewById<Button>(R.id.lazyBtn)
        lazyBtn?.setOnClickListener{
            viewModel.setMoodData("Lazy")
        }
        val motivatedBtn = view.findViewById<Button>(R.id.motivatedBtn)
        motivatedBtn?.setOnClickListener{
            viewModel.setMoodData("Motivated")
        }
        return view
    }
}