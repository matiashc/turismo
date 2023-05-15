package co.edu.ufps.sitiosturisticos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    var TAG: String="MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = Firebase.auth
        val email = findViewById<TextInputEditText>(R.id.textUsuario)
        val password = findViewById<TextInputEditText>(R.id.textClave)
        val inicio = findViewById<Button>(R.id.iniciar)
        inicio.setOnClickListener {
            signIn(email.text.toString(),password.text.toString())
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
      //  updateUI(currentUser)
    }

    public fun signIn(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    Toast.makeText(baseContext, "Authentication exitosa.",
                        Toast.LENGTH_SHORT).show()
                    val user = auth.currentUser
                    irInicio()
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                  // updateUI(null)
                }
            }

    }

    fun irInicio(){
        val intent = Intent(this,HomeActivity::class.java)
        startActivity(intent)
    }

}