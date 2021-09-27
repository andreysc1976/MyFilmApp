package ru.a_party.myfilmapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Section(val name: String):Parcelable {
    var films:List<Film> = listOf();
}