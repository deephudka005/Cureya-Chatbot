package com.example.cureya_chatbot

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_intro_page4.*

class Intro_page4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_page4)
        val load_video_uri : Uri = Uri.parse("android.resource://$packageName/${R.raw.load_animation}")
        load_video.setVideoURI(load_video_uri)
        load_video.requestFocus()
        load_video.start()
        load_video.setOnCompletionListener {
            val loginpage = Intent(this,login_page::class.java)
            startActivity(loginpage)
            finish()
        }
    }
}