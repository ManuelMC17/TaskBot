package com.example.taskbot.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.taskbot.utils.FragmentCommunicator
import com.example.taskbot.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), FragmentCommunicator {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun showLoader(value: Boolean) {
        binding.view.visibility = if (value) View.VISIBLE else View.GONE
    }
}