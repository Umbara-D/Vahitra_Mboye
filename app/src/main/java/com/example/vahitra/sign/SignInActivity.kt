package com.example.vahitra.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.vahitra.home.HomeActivity
import com.example.vahitra.R
import com.example.vahitra.utils.Preferences
import com.google.firebase.database.*

class SignInActivity : AppCompatActivity() {

    lateinit var etUsername: EditText
    lateinit var etPassword: EditText
    lateinit var iUsername:String
    lateinit var iPassword:String
    lateinit var database: DatabaseReference
    lateinit var preference: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        database = FirebaseDatabase.getInstance().getReference("User")
        preference = Preferences(this)

        preference.setValues("onboarding", "1")
        if(preference.getValues("status").equals("1")){
            finishAffinity()

            var goHome = Intent(this@SignInActivity, HomeActivity::class.java)
            startActivity(goHome)
        }

        val btn_login : Button = findViewById(R.id.btn_login)
        etUsername = findViewById(R.id.et_user)
        etPassword = findViewById(R.id.et_password)

        btn_login.setOnClickListener{

            iUsername = etUsername.text.toString()
            iPassword = etPassword.text.toString()

            if(iUsername.equals("")){
                etUsername.error = "Silahkan Isi Username"
                etUsername.requestFocus()
            }
            else if(iPassword.equals("")){
                etUsername.error = "Silahkan Isi Password"
                etPassword.requestFocus()
            }
            else{
                pushLogin(iUsername, iPassword)
            }
        }

        val btn_signup : Button = findViewById(R.id.btn_signup)
        btn_signup.setOnClickListener{
            var signup = Intent(this@SignInActivity, SignUpActivity::class.java)
            startActivity(signup)
        }
    }

    private fun pushLogin(iUsername: String, iPassword: String) {
        database.child(iUsername).addValueEventListener(object : ValueEventListener{

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@SignInActivity, databaseError.message,
                    Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                var user = snapshot.getValue(User::class.java)

                if(user == null){
                    Toast.makeText(this@SignInActivity, "User Tidak Ditemukan",
                        Toast.LENGTH_LONG).show()
                }
                else{
                    if(user.password.equals(iPassword)){

                        preference.setValues("nama", user.nama.toString())
                        preference.setValues("user", user.username.toString())
                        preference.setValues("url", user.url.toString())
                        preference.setValues("email", user.email.toString())
                        preference.setValues("saldo", user.saldo.toString())
                        preference.setValues("status", "1")

                        var intent = Intent(this@SignInActivity, HomeActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@SignInActivity, "Password Anda Salah",
                            Toast.LENGTH_LONG).show()
                    }
                }
            }

        })
    }


}