package ru.a_party.myfilmapp.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.squareup.picasso.Picasso
import org.w3c.dom.Text
import ru.a_party.myfilmapp.R
import ru.a_party.myfilmapp.model.EXTRA_DETAIL_FILM
import ru.a_party.myfilmapp.model.MovieListResultObject

class FilmFragment : Fragment() {

    val loadResultsReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            TODO("Not yet implemented")
        }
    }

        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_film, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val film = arguments?.getParcelable<MovieListResultObject>(BUNDLE_EXTRA)
        film?.let{
            view.findViewById<TextView>(R.id.textViewCardFilmTitle).text=it.title
            view.findViewById<TextView>(R.id.textViewCardFilmYear).text=it.release_date
            view.findViewById<TextView>(R.id.textViewDesc).text = it.overview
            Picasso.get().load("https://image.tmdb.org/t/p/w342/${it.poster_path}?language-ru").into(view.findViewById(R.id.imageView4) as ImageView)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let {
            LocalBroadcastManager.getInstance(it)
                .registerReceiver(loadResultsReceiver, IntentFilter(EXTRA_DETAIL_FILM))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        context?.let {
        LocalBroadcastManager.getInstance(it).unregisterReceiver(loadResultsReceiver)
        }
    }

    companion object {

        const val BUNDLE_EXTRA="TMDB_FILMS"

        @JvmStatic
        fun newInstance(bundle: Bundle):FilmFragment{
                val filmFragment = FilmFragment()
                filmFragment.arguments = bundle
                return filmFragment
            }
    }
}

