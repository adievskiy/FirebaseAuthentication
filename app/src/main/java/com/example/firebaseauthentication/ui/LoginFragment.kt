package com.example.firebaseauthentication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.firebaseauthentication.R
import com.example.firebaseauthentication.databinding.FragmentLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import render.animations.*

@Suppress("DEPRECATION")
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val auth = Firebase.auth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.confirmBTN.setOnClickListener {
            val email = binding.emailET.text.toString()
            val pass = binding.passET.text.toString()
            if (email.isBlank() || pass.isBlank()) {
                val renderOne = Render(requireContext())
                val renderTwo = Render(requireContext())
                renderOne.setAnimation(Attention().Wobble(binding.emailET))
                renderOne.start()
                renderTwo.setAnimation(Attention().Wobble(binding.passET))
                renderTwo.start()
                Toast.makeText(requireContext(), "Поля должны быть заполнены", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }
            auth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(requireActivity()) {
                if (it.isSuccessful) {
                    requireFragmentManager().beginTransaction()
                        .replace(R.id.main, RecyclerFragment())
                        .commit()
                } else {
                    Toast.makeText(requireContext(), "Пользователь не найден", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
        binding.notRegisteredTV.setOnClickListener {
            requireFragmentManager().beginTransaction()
                .replace(R.id.main, RegisterFragment())
                .commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}