package com.example.cureya.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cureya_chatbot.R

class ChatbotAdapter: RecyclerView.Adapter<ChatViewHolder>(){
    private val items: ArrayList<Chat> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false)
        val viewHolder = ChatViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
    val current_message= items[position]
       // holder.bot_message.text=current_message.bot_text
        holder.user_message.text=current_message.user_text
        //Glide.with(holder.itemView.context).load(current_message.imageUrl).into(holder.profile_image)
    }

    override fun getItemCount(): Int {
        return items.size
    }
    fun updateNews(updatedmessage: ArrayList<Chat>){
        items.clear()
        items.addAll(updatedmessage)
        notifyDataSetChanged()
    }
}


class ChatViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    //val bot_message: TextView= itemView.findViewById(R.id.tv_bot_message)
    val user_message: TextView= itemView.findViewById(R.id.tv_message)
    //val profile_image: ImageView=itemView.findViewById(R.id.profileimage)
}