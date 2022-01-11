package com.example.cureya_chatbot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.cureya_chatbot.fragments.ChatbotFragment
import com.example.cureya_chatbot.fragments.HelpFragment
import com.example.cureya_chatbot.fragments.HomeFragment
import kotlinx.android.synthetic.main.activity_home_page.*

class home_page : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val chatbotFragment = ChatbotFragment()
    private val helpFragment= HelpFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*if (savedInstanceState == null) {
            bottom_navigation.setSelectedItemId(R.id.ic_home) // change to whichever id should be default
        }*/
        setContentView(R.layout.activity_home_page)
        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_home -> replaceFragment(homeFragment)
                R.id.ic_chatbot -> replaceFragment(chatbotFragment)
                R.id.ic_help -> replaceFragment(helpFragment)
            }
            true
        }
    }
    private fun replaceFragment(fragment: Fragment){
        val transaction= supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}