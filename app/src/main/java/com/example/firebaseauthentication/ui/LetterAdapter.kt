package com.example.firebaseauthentication.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseauthentication.R

class LetterAdapter : RecyclerView.Adapter<LetterAdapter.LetterViewHolder>() {

    private var letterList: List<Letter> = Letter.letter

    inner class LetterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val letterNameTV: TextView = itemView.findViewById(R.id.letterNameTV)
        val letterBodyTV: TextView = itemView.findViewById(R.id.letterBodyTV)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): LetterAdapter.LetterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_list, parent, false)
        return LetterViewHolder(view)
    }

    override fun onBindViewHolder(holder: LetterAdapter.LetterViewHolder, position: Int) {
        val letter = letterList[position]
        holder.letterNameTV.text = letter.name
        holder.letterBodyTV.text = letter.body
    }

    override fun getItemCount(): Int {
        return letterList.size
    }
}