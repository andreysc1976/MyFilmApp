package ru.a_party.myfilmapp.model

sealed class LoadExtraFilmState{
    data class Success(val data:FullFilmInfo)
}

