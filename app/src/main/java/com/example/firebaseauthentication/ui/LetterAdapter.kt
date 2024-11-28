package com.example.firebaseauthentication.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseauthentication.R

class LetterAdapter(private val letterList: MutableList<Letter>) :
    RecyclerView.Adapter<LetterAdapter.LetterViewHolder>() {

    class LetterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val letterNameTV: TextView = view.findViewById(R.id.letterNameTV)
        val letterBodyTV: TextView = view.findViewById(R.id.letterBodyTV)

        fun bind(letter: Letter) {
            letterNameTV.text = letter.header
            letterBodyTV.text = letter.body
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): LetterAdapter.LetterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_list, parent, false)
        return LetterViewHolder(view)
    }

    override fun onBindViewHolder(holder: LetterAdapter.LetterViewHolder, position: Int) {
        val letter = letterList[position]
        holder.letterNameTV.text = letter.header
        holder.letterBodyTV.text = letter.body
    }

    override fun getItemCount(): Int {
        return letterList.size
    }

}