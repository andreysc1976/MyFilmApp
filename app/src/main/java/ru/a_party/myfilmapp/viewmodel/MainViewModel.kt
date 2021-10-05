package ru.a_party.myfilmapp.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import ru.a_party.myfilmapp.model.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

class MainViewModel : ViewModel() {
    val liveDataToObserve: MutableLiveData<LoadState> = MutableLiveData()
    val liveData:LiveData<LoadState> = liveDataToObserve

    @RequiresApi(Build.VERSION_CODES.N)
    fun getSectionData() = getDataSectionFromServer()

    companion object {
        val API_KEY:String="96641b8ec980778507a84bc711efa606"
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getDataFromInet(uri:String):String{
        var connection: HttpsURLConnection?=null
        var result:String="";
        val url: URL = URL(uri+"?api_key=${LoadSectionData.API_KEY}&language=ru&page=1")
        try {
            connection = url.openConnection() as HttpsURLConnection
            connection.apply {
                requestMethod = "GET"
            }

            val ff = connection.responseCode
            val reader = BufferedReader(InputStreamReader(connection.inputStream))
            result = reader.lines().collect(Collectors.joining(System.lineSeparator()))
        } catch (e: Exception){
            Log.e("ViewModel","FAILED INET",e)
        } finally {
            connection?.disconnect()
        }
        return result
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getDataSectionFromServer() {
        liveDataToObserve.value = LoadState.Loading
        Thread{

            var sec1 = Gson().fromJson(getDataFromInet("https://api.themoviedb.org/3/movie/popular"),SectionDataClass::class.java)
            var section1:Section = Section("Популярные","https://api.themoviedb.org/3/movie/popular")
            section1.films = sec1.results.asList()

            var sec2 = Gson().fromJson(getDataFromInet("https://api.themoviedb.org/3/movie/now_playing"),SectionDataClass::class.java)
            var section2:Section = Section("Сейчас в кино","https://api.themoviedb.org/3/movie/now_playing")
            section2.films = sec2.results.asList()
            var sec3 = Gson().fromJson(getDataFromInet("https://api.themoviedb.org/3/movie/top_rated"),SectionDataClass::class.java)
            var section3:Section = Section("В ТОПе","https://api.themoviedb.org/3/movie/top_rated")
            section3.films = sec3.results.asList()

            var sections:List<Section> = listOf(section1,section2,section3)

            liveDataToObserve.postValue(LoadState.Success(sections))
        }.start()
    }


}