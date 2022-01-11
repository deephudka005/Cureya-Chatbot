package com.example.cureya_chatbot.model

data class User(var uid: String ="",
                var displayName:String?="",
                var email: String="",
                val imageUrl: String="")