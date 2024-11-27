package com.example.firebaseauthentication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.firebaseauthentication.R
import com.example.firebaseauthentication.databinding.FragmentRegisterBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import render.animations.Attention
import render.animations.*

@Suppress("DEPRECATION")
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val auth = Firebase.auth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registerBTN.setOnClickListener {
            val email = binding.emailET.text.toString()
            val pass = binding.passET.text.toString()
            val confirmPass = binding.confirmPassET.text.toString()
            if (email.isBlank() || pass.isBlank() || confirmPass.isBlank()) {
                val renderOne = Render(requireContext())
                val renderTwo = Render(requireContext())
                val renderThree = Render(requireContext())
                renderOne.setAnimation(Attention().Wobble(binding.emailET))
                renderOne.start()
                renderTwo.setAnimation(Attention().Wobble(binding.passET))
                renderTwo.start()
                renderThree.setAnimation(Attention().Wobble(binding.confirmPassET))
                renderThree.start()
                Toast.makeText(requireContext(), "Поля не могут быть пустыми", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }
            if (pass != confirmPass) {
                val renderOne = Render(requireContext())
                val renderTwo = Render(requireContext())
                renderOne.setAnimation(Attention().Wobble(binding.passET))
                renderOne.start()
                renderTwo.setAnimation(Attention().Wobble(binding.confirmPassET))
                renderTwo.start()
                Toast.makeText(requireContext(), "Пароли не совпадают", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (pass.length < 6) {
                val renderOne = Render(requireContext())
                val renderTwo = Render(requireContext())
                renderOne.setAnimation(Attention().Wobble(binding.passET))
                renderOne.start()
                renderTwo.setAnimation(Attention().Wobble(binding.confirmPassET))
                renderTwo.start()
                Toast.makeText(requireContext(), "Пароль не менее 6 символов", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            createUser(email, pass)
        }
        binding.alreadyTV.setOnClickListener {
            requireFragmentManager().beginTransaction()
                .replace(R.id.main, LoginFragment())
                .commit()
        }
    }

    private fun createUser(email: String, pass: String) {
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(requireActivity()) {
                if (it.isSuccessful) {
                    Toast.makeText(requireContext(), "Успешная регистрация", Toast.LENGTH_LONG)
                        .show()
                    requireFragmentManager().beginTransaction()
                        .replace(R.id.main, LoginFragment())
                        .commit()
                } else {
                    if (auth.currentUser != null) {
                        Toast.makeText(requireContext(), "Пользователь уже существует", Toast.LENGTH_LONG)
                            .show()
                        requireFragmentManager().beginTransaction()
                            .replace(R.id.main, LoginFragment())
                            .commit()
                    }
                    Toast.makeText(requireContext(), "Регистрация не прошла", Toast.LENGTH_LONG)
                        .show()
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}