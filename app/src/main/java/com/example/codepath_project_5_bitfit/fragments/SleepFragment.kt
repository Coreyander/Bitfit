package com.example.codepath_project_5_bitfit.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.codepath_project_5_bitfit.R

class SleepFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sleep, container, false)
        return view
    }
    /**
     * The sleep fragment can log a set number of sleep hours
     * implement this with a fill bar that goes up to 24 hours
     *
     */
}