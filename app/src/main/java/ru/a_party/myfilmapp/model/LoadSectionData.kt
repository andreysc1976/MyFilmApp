package ru.a_party.myfilmapp.model

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL
import java.util.stream.Collector
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

class LoadSectionData(var uri:String,val page:Int): Thread() {
    private var onLoadDataUpdater:OnLoadDataUpdater?=null;

    companion object {
        val API_KEY:String="96641b8ec980778507a84bc711efa606"
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun run() {
        var connection:HttpsURLConnection?=null
        val url: URL = URL(uri+"?api_key=${API_KEY}&language=ru&page=${page}")
        try {
            connection = url.openConnection() as HttpsURLConnection
            connection.apply {
                requestMethod = "GET"
                readTimeout = 10000
                addRequestProperty("api_key", API_KEY)
                addRequestProperty("page", page.toString())
                addRequestProperty("language", "RU_ru")
            }

            val ff = connection.responseCode
            val reader = BufferedReader(InputStreamReader(connection.inputStream))
            val result = reader.lines().collect(Collectors.joining(System.lineSeparator()))
            var data: SectionDataClass = Gson().fromJson(result, SectionDataClass::class.java)
            onLoadDataUpdater?.onLoadData(data)
        } catch (e:Exception){
            Log.e("GGFFSS","FAILED",e)
        } finally {
            connection?.disconnect()
        }
    }

    fun setOnLoadDataUpdater(onLoadDataUpdater: OnLoadDataUpdater){
        this.onLoadDataUpdater = onLoadDataUpdater
    }

    interface OnLoadDataUpdater {
        fun onLoadData(data:SectionDataClass)
    }
}