package ru.a_party.myfilmapp.view

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.w3c.dom.Text
import ru.a_party.myfilmapp.R
import ru.a_party.myfilmapp.model.MovieListResultObject

class FilmFragment : Fragment() {

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
            val uri: Uri = Uri.parse("https://image.tmdb.org/t/p/w154/"+it.poster_path)
            uri?.let {
                //view.findViewById<ImageView>(R.id.imageView).setImageBitmap()
            }
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

