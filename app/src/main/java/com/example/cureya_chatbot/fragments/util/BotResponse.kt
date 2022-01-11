package com.example.cureya_chatbot.fragments.util

import java.util.*

object BotResponse {
    fun basicResponses(_message: String): String {

        val random = (0..2).random()
        val message = _message.lowercase(Locale.getDefault())

        return when {

            //Flips a coin
            message.contains("headache") -> {

                " Headache can be reduced by doing following things:\n".plus(
                        "1. Rest in a quiet, dark room\n").plus(
                        "2. Hot or cold compresses to your head or neck\n").plus(
                        "3. Massage and small amounts of caffeine\n").plus(
                        "4. Over-the-counter medications such as ibuprofen (Advil, Motrin IB, others), acetaminophen (Tylenol, others) and aspirin\n").plus(
                        "5.Prescription medications including triptans, such as  sumatriptan (Imitrex) and zolmitriptan (Zomig)\n").plus(
                        "6.Preventive medications such as metoprolol (Lopressor), propranolol (Innopran, Inderal, others), amitriptyline, divalproex (Depakote), topiramate (Qudexy XR, Trokendi XR ,Topamax) or erenumab-aooe (Aimovig)")
            }
            message.contains("ok fine")->{
                "You can start exercising daily to prevent stress and headaches.\n Take Yoga Classes from \n Card Details"
            }


            //How are you?
            message.contains("how are you") -> {
                when (random) {
                    0 -> "I'm doing fine, thanks!"
                    1 -> "I'm hungry..."
                    2 -> "Pretty good! How about you?"
                    else -> "error"
                }
            }


            //When the programme doesn't understand...
            else -> {
                when (random) {
                    0 -> "I don't understand..."
                    1 -> "Try asking me something different"
                    2 -> "Idk"
                    else -> "error"
                }
            }
        }
    }
}