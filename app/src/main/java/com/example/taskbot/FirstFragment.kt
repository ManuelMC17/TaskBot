package com.example.taskbot

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.taskbot.R
import com.example.taskbot.databinding.FragmentPendientesBinding
import com.example.taskbot.utils.FragmentCommunicator
import com.example.taskbot.view.MainActivity


class FirstFragment : Fragment() {

    private var _binding: FragmentPendientesBinding? = null
    private val binding get() = _binding!!
    private lateinit var communicator: FragmentCommunicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        communicator = requireActivity() as MainActivity
        _binding = FragmentPendientesBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    private fun setupView(){
        binding.iconAdd.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment2)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}