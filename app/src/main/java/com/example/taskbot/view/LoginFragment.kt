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
import com.example.taskbot.databinding.FragmentLoginBinding
import com.example.taskbot.utils.FragmentCommunicator
import com.example.taskbot.viewModel.LoginViewModel


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<LoginViewModel>()
    var isValid: Boolean = false
    private lateinit var communicator: FragmentCommunicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        communicator = requireActivity() as MainActivity
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        setupObservers()
        setupView()
        return binding.root
    }

    private fun setupView() {

        binding.registrar.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrerFragment)
        }

        binding.button.setOnClickListener {
            if (validateInputs()) {
                requestLogin()
            } else {
                Toast.makeText(activity, "Correo y contraseña son obligatorios", Toast.LENGTH_SHORT).show()
            }
        }

        binding.correito.addTextChangedListener {

            if (binding.correito.text.toString().isEmpty()) {
                binding.correo.error = "Introduce un correo"
                isValid = false
            } else {
                isValid = true
            }
        }
        binding.contrasenita.addTextChangedListener {

            if (binding.contrasenita.text.toString().isEmpty()) {
                binding.contrasenia.error = "Introduce tu contraseña"
                isValid = false
            } else {
                isValid = true
            }
        }
        setupObservers()
    }

    private fun validateInputs(): Boolean {
        val emailNotEmpty = binding.correito.text.toString().isNotEmpty()
        val passwordNotEmpty = binding.contrasenita.text.toString().isNotEmpty()

        isValid = emailNotEmpty && passwordNotEmpty

        binding.correo.error = if (!emailNotEmpty) "Introduce un correo" else null
        binding.contrasenia.error = if (!passwordNotEmpty) "Introduce tu contraseña" else null

        return isValid
    }

    private fun setupObservers() {
        viewModel.loaderState.observe(viewLifecycleOwner) { loaderState ->
            communicator.showLoader(loaderState)
        }
        viewModel.sessionValid.observe(viewLifecycleOwner) { validSession ->
            if (validSession) {
                findNavController().navigate(R.id.action_loginFragment_to_firstFragment)
            } else {
                Toast.makeText(activity, "Ingreso no valido", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun requestLogin() {
        viewModel.requestSignIn(binding.correito.text.toString(),
            binding.contrasenita.text.toString())
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}