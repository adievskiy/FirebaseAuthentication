package com.example.firebaseauthentication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseauthentication.R
import com.example.firebaseauthentication.databinding.FragmentRecyclerBinding
import com.example.firebaseauthentication.databinding.FragmentRegisterBinding

class RecyclerFragment : Fragment() {

    private var _binding: FragmentRecyclerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentRecyclerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.letterListRV.layoutManager = LinearLayoutManager(requireContext())
        binding.letterListRV.adapter = LetterAdapter()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}