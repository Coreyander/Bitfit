package com.example.codepath_project_5_bitfit.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import com.example.codepath_project_5_bitfit.R

class MoodFragment() : Fragment() {

    private val viewModel: FragmentViewModel by activityViewModels()

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
            viewModel.setMood("Happy")
        }
        val sadBtn = view.findViewById<Button>(R.id.sadBtn)
        sadBtn?.setOnClickListener{
            viewModel.setMood("Sad")
        }
        val contentBtn = view.findViewById<Button>(R.id.contentBtn)
        contentBtn?.setOnClickListener{
            viewModel.setMood("Content")
        }
        val fearBtn = view.findViewById<Button>(R.id.fearBtn)
        fearBtn?.setOnClickListener{
            viewModel.setMood("Fearful")
        }
        val angerBtn = view.findViewById<Button>(R.id.angerBtn)
        angerBtn?.setOnClickListener{
            viewModel.setMood("Angry")
        }
        val lazyBtn = view.findViewById<Button>(R.id.lazyBtn)
        lazyBtn?.setOnClickListener{
            viewModel.setMood("Lazy")
        }
        val motivatedBtn = view.findViewById<Button>(R.id.motivatedBtn)
        motivatedBtn?.setOnClickListener{
            viewModel.setMood("Motivated")
        }
        return view
    }
}