package com.example.cureya_chatbot.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cureya.fragments.ChatbotAdapter
import com.example.cureya_chatbot.R
import com.example.cureya_chatbot.fragments.util.BotResponse
import com.example.cureya_chatbot.fragments.util.Constants.RECEIVE_ID
import com.example.cureya_chatbot.fragments.util.Constants.SEND_ID
import com.example.cureya_chatbot.fragments.util.Time
import kotlinx.android.synthetic.main.fragment_chatbot.*
import kotlinx.coroutines.*

class ChatbotFragment : Fragment() {
    //   private lateinit var mAdapter: ChatbotAdapter
    var messagesList = mutableListOf<customChat>()
    lateinit var mAdapter: MessagingAdapter
    //functions

    override fun onStart() {
        super.onStart()
        //In case there are messages, scroll to bottom when re-opening app
        GlobalScope.launch {
            delay(100)
            withContext(Dispatchers.Main) {
                rv_messages.scrollToPosition(mAdapter.itemCount - 1)
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_chatbot, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter=MessagingAdapter()
        rv_messages.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = mAdapter
        }
        btn_send.setOnClickListener {
            val message = et_message.text.toString()
            val timeStamp = Time.timeStamp()

            if (message.isNotEmpty()) {
                //Adds it to our local list
                messagesList.add(customChat(message, SEND_ID, timeStamp))
                et_message.setText("")

                mAdapter.insertMessage(customChat(message, SEND_ID, timeStamp))
                rv_messages.scrollToPosition(mAdapter.itemCount - 1)

                botResponse(message)
            }
        }

        //Scroll back to correct position when user clicks on text view
        et_message.setOnClickListener {
            GlobalScope.launch {
                delay(100)

                withContext(Dispatchers.Main) {
                    rv_messages.scrollToPosition(mAdapter.itemCount - 1)

                }
            }
        }
        customBotMessage("Hi")


    }

    private fun customBotMessage(message: String) {
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                val timeStamp = Time.timeStamp()
                messagesList.add(customChat(message, RECEIVE_ID, timeStamp))
                mAdapter.insertMessage(customChat(message, RECEIVE_ID, timeStamp))

                rv_messages.scrollToPosition(mAdapter.itemCount - 1)
            }
        }
    }


    private fun botResponse(message: String) {
        val timeStamp = Time.timeStamp()

        GlobalScope.launch {
            //Fake response delay
            delay(1000)

            withContext(Dispatchers.Main) {
                //Gets the response
                val response = BotResponse.basicResponses(message)

                //Adds it to our local list
                messagesList.add(customChat(response, RECEIVE_ID, timeStamp))

                //Inserts our message into the adapter
                mAdapter.insertMessage(customChat(response, RECEIVE_ID, timeStamp))

                //Scrolls us to the position of the latest message
                rv_messages.scrollToPosition(mAdapter.itemCount - 1)

            }
        }
    }

}
