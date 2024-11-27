package com.example.firebaseauthentication.ui

class Letter(val name: String, val body: String) {
    companion object {
        val letter = mutableListOf(
            Letter("Письмо 1", "Тело письма 1"),
            Letter("Письмо 2", "Тело письма 2"),
            Letter("Письмо 3", "Тело письма 3"),
        )
    }
}