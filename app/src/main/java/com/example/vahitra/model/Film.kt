package com.example.vahitra.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Film (
    var desc:String? = "",
    var director:String? = "",
    var genre:String? = "",
    var judul:String? = "",
    var poster:String? = "",
    var rating:String? = "",
) : Parcelable