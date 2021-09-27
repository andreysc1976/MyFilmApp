package ru.a_party.myfilmapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.a_party.myfilmapp.R
import ru.a_party.myfilmapp.model.Film

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class FilmFragment : Fragment() {
    private lateinit var film:Film;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_film, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val film = arguments?.getParcelable<Film>(BUNDLE_EXTRA)
        if (film!=null){
            view.findViewById<TextView>(R.id.textViewCardFilmTitle).text=film.title
            view.findViewById<TextView>(R.id.textViewCardFilmYear).text=film.year.toString();
        }
    }

    companion object {

        const val BUNDLE_EXTRA="tmdb_film"

        @JvmStatic
        fun newInstance(bundle: Bundle):FilmFragment{
                val filmFragment = FilmFragment()
                filmFragment.arguments = bundle
                return filmFragment
            }
    }
}