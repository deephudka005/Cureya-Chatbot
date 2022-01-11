package com.example.cureya_chatbot.dao

import com.example.cureya_chatbot.model.User
import com.example.cureya_chatbot.model.customUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserDao {
    private val db = FirebaseFirestore.getInstance()
    private val userscollection =db.collection("users")
    private val customuserscollection=db.collection("customUser")

    fun addUser(user: User?){
        user?.let {
            GlobalScope.launch(Dispatchers.IO) {
                userscollection.document(user.uid).set(it)
            }
        }
    }
    fun addCustomUser(user: customUser?){
        user?.let {
            GlobalScope.launch(Dispatchers.IO) {
                customuserscollection.document(user.uid).set(it)
            }
        }
    }
}