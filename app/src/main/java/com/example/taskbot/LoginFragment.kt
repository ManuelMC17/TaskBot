package com.example.taskbot

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.taskbot.R
import com.example.taskbot.databinding.FragmentLoginBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    var isValid: Boolean = false



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    private fun setupView() {
        binding.textView.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrerFragment)
        }

        binding.button.setOnClickListener {
            if (isValid) {
                //requestLogin()
            } else {
                Toast.makeText(activity, "Ingreso invalido", Toast.LENGTH_SHORT).show()
            }
        }
        binding.correito.addTextChangedListener {
            if (binding.correito.text.toString().isEmpty()) {
                binding.correo.error = "Escribe tu correo"
                isValid = false
            } else {
                isValid = true
            }
        }
        binding.contrasenita.addTextChangedListener {
            if (binding.contrasenita.text.toString().isEmpty()) {
                binding.contrasenita.error = "Por favor introduce tu contraseña"
                isValid = false
            } else {
                isValid = true
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}