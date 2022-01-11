package com.example.cureya_chatbot

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cureya_chatbot.dao.UserDao
import com.example.cureya_chatbot.model.User
import com.example.cureya_chatbot.model.customUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_intro_page3.*
import kotlinx.android.synthetic.main.activity_intro_page3.bot_video
import kotlinx.android.synthetic.main.activity_login_page.*
import kotlinx.android.synthetic.main.activity_signup_page.*

class signup_page : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private var email=""
    private var password=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_page)
        val signup_video_uri : Uri = Uri.parse("android.resource://$packageName/${R.raw.signup_animation}")
        signup_video.setVideoURI(signup_video_uri)
        signup_video.requestFocus()
        signup_video.start()
        signup_video.setOnCompletionListener {
            signup_video.start()
        }
        auth=Firebase.auth
        signup_button.setOnClickListener {

            val userdisplayName= first_name.text.toString().plus(" ").plus(last_name.text.toString())
            if (password1.text.toString() == password_confirm.text.toString()) {
                val user= customUser()
                user.uid=emailId.text.toString()
                user.displayName=userdisplayName
                user.email=emailId.text.toString()
                user.password=password1.text.toString()
                val usersDao= UserDao()
                usersDao.addCustomUser(user)
                val home_page = Intent(this, home_page::class.java)
                startActivity(home_page)
                finish()

            } else {
                Toast.makeText(
                    baseContext, "Please Enter same Passwords",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}

/*email=username.text.toString().trim()
            password=user_password.text.toString().trim()
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener {

                    }
                    .addOnFailureListener {e->
                        Toast.makeText(this,"Signup Failed due to ${e.message}",Toast.LENGTH_SHORT).show()
                    }
                    first_name.text.toString().plus(last_name.text.toString()).plus()
 */