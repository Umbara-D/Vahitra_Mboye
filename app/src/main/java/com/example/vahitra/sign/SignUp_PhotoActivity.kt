package com.example.vahitra.sign

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.vahitra.home.HomeActivity
import com.example.vahitra.R
import com.example.vahitra.utils.Preferences
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import java.util.*

class SignUp_PhotoActivity : AppCompatActivity(), PermissionListener {

    lateinit var hello: TextView
    lateinit var btn_save: Button
    lateinit var btn_skip: Button
    lateinit var img_add: ImageView
    lateinit var img_profile: ImageView

    val REQUEST_IMAGE_CAPTURE = 1
    var StatusAdd:Boolean = false
    lateinit var filepath: Uri

    lateinit var storage: FirebaseStorage
    lateinit var storageReferensi: StorageReference
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up__photo)

        preferences = Preferences(this)
        storage = FirebaseStorage.getInstance()
        storageReferensi = storage.getReference()

        hello = findViewById(R.id.text_hello)
        btn_save = findViewById(R.id.btn_save)
        btn_skip = findViewById(R.id.btn_skip)
        img_add = findViewById(R.id.img_add)
        img_profile = findViewById(R.id.img_profile)

        hello.text = "Selamat Datang,\n"+intent.getStringExtra("nama")

        img_add.setOnClickListener {
            if(StatusAdd){
                StatusAdd = false
                btn_save.visibility = View.VISIBLE
                img_add.setImageResource(R.drawable.ic_add_photo)
                img_profile.setImageResource(R.drawable.user_pic)

            }
            else{
                Dexter.withActivity(this)
                        .withPermission(Manifest.permission.CAMERA)
                        .withListener(this)
                        .check()
            }
        }

        btn_skip.setOnClickListener {
            finishAffinity()
            var skip = Intent(this@SignUp_PhotoActivity, HomeActivity::class.java)
            startActivity(skip)
        }

        btn_save.setOnClickListener {
            if(filepath !=  null){
                var progressDialog = ProgressDialog(this)
                progressDialog.setTitle("Uploading...")
                progressDialog.show()

                var ref = storageReferensi.child("images/"+UUID.randomUUID().toString())
                ref.putFile(filepath)
                        .addOnSuccessListener {
                            progressDialog.dismiss()
                            Toast.makeText(this, "Berhasil Diupload", Toast.LENGTH_LONG).show()

                            ref.downloadUrl.addOnSuccessListener {
                                preferences.setValues("url", it.toString())
                            }
                        }
                        .addOnFailureListener {
                            progressDialog.dismiss()
                            Toast.makeText(this, "Gagal Diupload", Toast.LENGTH_LONG).show()
                        }
                        .addOnProgressListener {
                            taskSnapshot -> var progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                            progressDialog.setMessage("Upload "+progress.toInt()+" %")
                        }

            }
        }

    }

    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
            takePicture ->
            takePicture.resolveActivity(packageManager)?.also {
                startActivityForResult(takePicture, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
        TODO("Not yet implemented")
    }

    override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest?, token: PermissionToken?) {
        Toast.makeText(this, "Anda tidak dapat menambahkan Foto Profile", Toast.LENGTH_LONG).show()
    }

    override fun onBackPressed() {
        Toast.makeText(this, "Terburu-buru? Upload Nanti Saja", Toast.LENGTH_LONG).show()
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK){
            var bitmap = data?.extras?.get("data") as Bitmap
            StatusAdd = true

            filepath = data.getData()!!
            Glide.with(this)
                    .load(bitmap)
                    .apply(RequestOptions.circleCropTransform())
                    .into(img_profile)

            btn_save.visibility = View.VISIBLE
            img_add.setImageResource(R.drawable.ic_delete)

        }
    }
}