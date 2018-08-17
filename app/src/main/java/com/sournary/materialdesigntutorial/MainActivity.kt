package com.sournary.materialdesigntutorial

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity() {

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedViewModel = ViewModelProviders.of(this).get(SharedViewModel::class.java)
    }

    override fun onBackPressed() {
        sharedViewModel.backPressEvent.call()
    }
}
