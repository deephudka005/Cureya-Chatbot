package com.example.cureya_chatbot

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_intro_page3.*

class Intro_page3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_page3)
        val bot_video_uri : Uri = Uri.parse("android.resource://$packageName/${R.raw.bot_animation}")
        bot_video.setVideoURI(bot_video_uri)
        bot_video.requestFocus()
        bot_video.start()
        bot_video.setOnCompletionListener {
            bot_video.start()
        }
    }

    fun next_activity(view: android.view.View) {
        val introPage4 = Intent(this,Intro_page4::class.java)
        startActivity(introPage4)
        finish()
    }

    fun skiptologin(view: View) {
        val loginpage= Intent(this,login_page::class.java)
        startActivity(loginpage)
        finish()
    }
}