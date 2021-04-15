package com.example.vahitra.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.vahitra.R
import com.google.firebase.database.*

class SignUpActivity : AppCompatActivity() {

    lateinit var iUsername:String
    lateinit var iPassword:String
    lateinit var iFullname:String
    lateinit var iEmail:String
    lateinit var btn_lanjut: Button
    lateinit var et_username: EditText
    lateinit var et_password: EditText
    lateinit var et_fullname: EditText
    lateinit var et_email: EditText
    lateinit var img_back: ImageView

    lateinit var DBreference:DatabaseReference
    lateinit var FirebaseInstance: FirebaseDatabase
    lateinit var DB:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        et_username = findViewById(R.id.et_username)
        et_password = findViewById(R.id.et_sandi)
        et_email = findViewById(R.id.et_email)
        et_fullname = findViewById(R.id.et_fullname)
        btn_lanjut = findViewById(R.id.btn_lanjut)

        FirebaseInstance = FirebaseDatabase.getInstance()
        DB = FirebaseDatabase.getInstance().getReference()
        DBreference = FirebaseInstance.getReference("User")

        btn_lanjut.setOnClickListener {
            iUsername = et_username.text.toString()
            iPassword = et_password.text.toString()
            iFullname = et_fullname.text.toString()
            iEmail = et_email.text.toString()

            if(iUsername.equals("")){
                et_username.error = "Silahkan Masukan Username Anda"
                et_username.requestFocus()
            }
            else if(iPassword.equals("")){
                et_password.error = "Silahkan Masukan Password Anda"
                et_password.requestFocus()
            }
            if(iFullname.equals("")){
                et_fullname.error = "Silahkan Masukan Nama Lengkap Anda"
                et_fullname.requestFocus()
            }
            if(iEmail.equals("")){
                et_email.error = "Silahkan Masukan Email Anda"
                et_email.requestFocus()
            }
            else{
                saveAccount(iUsername, iPassword, iFullname, iEmail)
            }
        }

        img_back = findViewById(R.id.img_back)
        img_back.setOnClickListener {
            var signin = Intent(this@SignUpActivity, SignInActivity::class.java)
            startActivity(signin)
        }

    }

    private fun saveAccount(iUsername: String, iPassword: String, iFullname: String, iEmail: String) {
        var user = User()

        user.email = iEmail
        user.password = iPassword
        user.username = iUsername
        user.nama = iFullname

        if(iUsername != null){
            chechingUsername(iUsername, user)
        }
    }

    private fun chechingUsername(iUsername: String, data: User) {
        DBreference.child(iUsername).addValueEventListener(object : ValueEventListener{

            override fun onCancelled(dberror: DatabaseError) {
                Toast.makeText(this@SignUpActivity, ""+dberror.message, Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                var user = snapshot.getValue(User::class.java)
                if(user ==  null){
                    DBreference.child(iUsername).setValue(data)

                    var goSignUpPhoto = Intent(this@SignUpActivity,
                            SignUp_PhotoActivity::class.java).putExtra("nama", data.nama)
                    startActivity(goSignUpPhoto)
                }
                else{
                    Toast.makeText(this@SignUpActivity, "User Sudah Digunakan", Toast.LENGTH_LONG).show()
                }
            }

        })

    }
}