package com.example.taskbot.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.fragment.app.Fragment
import com.example.taskbot.R
import com.example.taskbot.databinding.FragmentRegistrerBinding
import com.example.taskbot.utils.FragmentCommunicator
import com.example.taskbot.viewModel.RegisterViewModel


class RegisterFragment : Fragment() {

    private var _binding: FragmentRegistrerBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<RegisterViewModel>()
    var isValid: Boolean = false
    private lateinit var communicator: FragmentCommunicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        communicator = requireActivity() as MainActivity
        _binding = FragmentRegistrerBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    private fun setupView() {
        binding.button.setOnClickListener {
            viewModel.requestSignUp(binding.correito.text.toString(),
                binding.contrasenias.text.toString())
            findNavController().navigate(R.id.action_registrerFragment_to_loginFragment)
        }

        binding.button.setOnClickListener {
            val email = binding.correito.text.toString().trim()
            val password = binding.contrasenias.text.toString().trim()
            val name = binding.name.text.toString().trim()

            if (name.isEmpty()) {
                binding.name.error = "El nombre es obligatorio"
                return@setOnClickListener
            }

            if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.correo.error = "Introduce un correo válido"
                return@setOnClickListener
            }

            if (password.isEmpty() || password.length < 6) {
                binding.contrasenias.error = "Introduce una contraseña de al menos 6 caracteres"
                return@setOnClickListener
            }

            viewModel.requestSignUp(email, password)
            findNavController().navigate(R.id.action_registrerFragment_to_loginFragment)

        }

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.loaderState.observe(viewLifecycleOwner) { loaderState ->
            communicator.showLoader(loaderState)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}