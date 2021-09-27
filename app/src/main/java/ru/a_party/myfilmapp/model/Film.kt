package ru.a_party.myfilmapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Film(val title: String,val year:Int, val id:Int) : Parcelable {
    var poster: String? = null

    private fun loadPoster(){

    }

}