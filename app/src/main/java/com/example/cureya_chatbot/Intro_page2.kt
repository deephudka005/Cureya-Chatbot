package com.example.cureya_chatbot

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_intro_page2.*

class Intro_page2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_page2)
        val doc_video_uri : Uri = Uri.parse("android.resource://$packageName/${R.raw.doc_animation}")
        doc_video.setVideoURI(doc_video_uri)
        doc_video.requestFocus()
        doc_video.start()
    }

    fun next_activity(view: android.view.View) {
        val introPage3 = Intent(this,Intro_page3::class.java)
        startActivity(introPage3)
        finish()
    }

    fun skiptologin(view: View) {
        val loginpage= Intent(this,login_page::class.java)
        startActivity(loginpage)
        finish()
    }
}