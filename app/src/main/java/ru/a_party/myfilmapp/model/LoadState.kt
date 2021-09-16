package ru.a_party.myfilmapp.model

sealed class LoadState {
    data class Success(val Data:Any) : LoadState()
    data class Error(val errror: Throwable) : LoadState()
    object Loading:LoadState()
}
