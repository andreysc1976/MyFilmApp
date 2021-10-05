package ru.a_party.myfilmapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieListResultObject(
    val poster_path:String?,
    val adult:Boolean,
    val overview:String,
    val release_date:String,
    val genre_ids:Array<Int>,
    val id:Int,
    val original_title:String,
    val original_language:String,
    val title:String,
    val backdrop_path:String?,
    val popularity:Float,
    val vote_count:Int,
    val video:Boolean,
    val vote_average:Float
): Parcelable