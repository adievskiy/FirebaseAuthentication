package com.example.firebaseauthentication.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebaseauthentication.databinding.FragmentRecyclerBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.database

@SuppressLint("NotifyDataSetChanged")
class RecyclerFragment : Fragment() {

    private var _binding: FragmentRecyclerBinding? = null
    private val binding get() = _binding!!
    private val letters = mutableListOf<Letter>()
    private lateinit var adapter: LetterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentRecyclerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = LetterAdapter(letters)
        binding.letterListRV.layoutManager = LinearLayoutManager(requireContext())
        binding.letterListRV.adapter = adapter

        binding.sendBTN.setOnClickListener {
            addLetter()
        }

        readLetters(adapter)

        binding.readBTN.setOnClickListener {
            readLetters(adapter)
        }
    }

    private fun readLetters(adapter: LetterAdapter) {
        Firebase.database("https://myproject-ca62c-default-rtdb.asia-southeast1.firebasedatabase.app/").reference
            .child("letters")
            .child(FirebaseAuth.getInstance().currentUser!!.uid).get()
            .addOnSuccessListener {
                letters.clear()
                for (element in it.children) {
                    val letter: Letter = element.getValue(Letter::class.java)!!
                    letters.add(letter)
                }
                adapter.notifyDataSetChanged()
            }
    }

    private fun addLetter() {
        val header = binding.inputHeaderET.text.toString()
        val body = binding.inputLetterET.text.toString()
        val letter = Letter(header, body)
        val database =
            Firebase.database("https://myproject-ca62c-default-rtdb.asia-southeast1.firebasedatabase.app/").reference
                .child("letters")
                .child(FirebaseAuth.getInstance().currentUser!!.uid)
        val map: HashMap<String, Letter> = HashMap()
        map[letter.header] = letter
        database.updateChildren(map as Map<String, Any>)
        binding.inputHeaderET.text.clear()
        binding.inputLetterET.text.clear()
        adapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}