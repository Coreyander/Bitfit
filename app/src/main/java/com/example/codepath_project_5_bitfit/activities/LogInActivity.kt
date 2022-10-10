package com.example.codepath_project_5_bitfit.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.codepath_project_5_bitfit.BitfitApplication
import com.example.codepath_project_5_bitfit.R
import com.example.codepath_project_5_bitfit.database.FitnessEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.sql.Date


class LogInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_on)

        val logOnButton = findViewById<Button>(R.id.logOnButton)
        logOnButton.setOnClickListener{
            val intent = Intent(this, MenuActivity::class.java)
            this.startActivity(intent)
        }
    }

}