package com.example.cureya_chatbot

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val heart_video_uri : Uri = Uri.parse("android.resource://$packageName/${R.raw.heart_animation}")
        heart_video.setVideoURI(heart_video_uri)
        heart_video.requestFocus()
        heart_video.start()



    }

    fun next_activity(view: android.view.View) {
        val introPage2 = Intent(this,Intro_page2::class.java)
        startActivity(introPage2)
        finish()
    }
}