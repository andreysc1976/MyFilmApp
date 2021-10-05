package ru.a_party.myfilmapp.model

data class SectionDataClass(
    val page:Int,
    val results:Array<MovieListResultObject>,
    val total_results:Int,
    val total_pages:Int
)
