package com.example.cureya_chatbot

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.cureya_chatbot.dao.UserDao
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_intro_page4.*
import kotlinx.android.synthetic.main.activity_login_page.*
import kotlinx.android.synthetic.main.activity_signup_page.*

class login_page : AppCompatActivity() {
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth
    private var RC_SIGN_IN= 89
    private var email=""
    private var password=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        done_video.visibility=View.GONE
        val login_video_uri : Uri = Uri.parse("android.resource://$packageName/${R.raw.login_animation}")
        login_video.setVideoURI(login_video_uri)
        login_video.requestFocus()
        login_video.start()
        login_video.setOnCompletionListener {
            login_video.start()
        }

        //GOOGLE SIGNIN
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id_auth))
            .requestEmail()
            .build()
        // Build a GoogleSignInClient with the options specified by gso.
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        auth= Firebase.auth
        //LOGIN BUTTON FUNCTION
        login_button.setOnClickListener {
            //RETREIVING USER FROM FIREBASE
            login()

        }



}
    override fun onStart() {
        super.onStart()
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }
    private fun updateUI(account: FirebaseUser?) {
        if(account!= null){
            val user= com.example.cureya_chatbot.model.User(
                account.uid,
                account.displayName,
                account.email.toString(),
                account.photoUrl.toString(),
            )
            val usersDao= UserDao()
            usersDao.addUser(user)

            val profileActivityIntent= Intent(this, home_page::class.java)
            startActivity(profileActivityIntent)
            finish()
        }else{
            google_login.visibility=View.VISIBLE
        }
    }
    fun google_signIn(view: View) {
        val signInIntent = googleSignInClient.signInIntent
        //getResult.launch(signInIntent)
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }
    fun apple_signIn(view: View) {}
    fun signup_activity(view: View) {
        val signup_intent= Intent(this, signup_page::class.java)
        startActivity(signup_intent)
        finish()
    }

    fun login() {
        email=username.text.toString().trim()
        password=user_password.text.toString().trim()
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                login_button.visibility= View.GONE
                done_video.visibility= View.VISIBLE
                signup.visibility= View.GONE
                signup_text.visibility= View.GONE
                val load_video_uri : Uri = Uri.parse("android.resource://$packageName/${R.raw.done_animation}")
                done_video.setVideoURI(load_video_uri)
                done_video.requestFocus()
                done_video.start()
                done_video.setOnCompletionListener {
                    val home_intent = Intent(this, home_page::class.java)
                    startActivity(home_intent)
                    finish()
                }
            }
            .addOnFailureListener {e->
                Toast.makeText(this,"Login Failed due to ${e.message}",Toast.LENGTH_SHORT).show()
            }


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Log.w("FIRE89", "Google sign in failed", e)
            }
        }

        // Pass the activity result back to the Facebook SDK

    }
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        google_login.visibility=View.GONE
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("FIRE89", "signInWithCredential:success")

                    val user = auth.currentUser
                    //Log.d("FIRE89","firebaseAuthWithGoogle: ${user?.displayName}")
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("FIRE89", "signInWithCredential:failure",task.exception)
                    updateUI(null)
                }
            }
    }
    companion object {
        private const val TAG = "GoogleActivity"
    }
}