package ru.a_party.myfilmapp.model

import android.app.IntentService
import android.content.Intent

val EXTRA_DETAIL_FILM = "EXTRA_DETAIl.FILM"
val EXTRA_FILM_ID = "EXTRA_FILM_ID"

class FilmDetailLoaderService(name:String="FilmDetailInfoService"): IntentService(name) {
    override fun onHandleIntent(intent: Intent?) {
            if (intent==null){
                onEmptyIntent()
            } else
            {
                val filmId = intent.getIntExtra(EXTRA_FILM_ID,-1)
                if (filmId==-1){
                    onEmptyData()
                } else {
                    loadFilmDetail(filmId)
                }
            }
    }

    private fun loadFilmDetail(filmId: Int) {

    }

    private fun onEmptyData() {
        TODO("Not yet implemented")
    }

    private fun onEmptyIntent() {
        TODO("Not yet implemented")
    }
}