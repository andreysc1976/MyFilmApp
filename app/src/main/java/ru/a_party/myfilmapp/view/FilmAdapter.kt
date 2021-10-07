package ru.a_party.myfilmapp.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.a_party.myfilmapp.R
import ru.a_party.myfilmapp.model.MovieListResultObject

class FilmAdapter(private var onItemViewClickListener: MainFragment.OnItemViewClickListener?): RecyclerView.Adapter<FilmAdapter.FilmHolder>() {
    var films:List<MovieListResultObject> = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value){
            field=value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmHolder {
        return FilmHolder(LayoutInflater.from(parent.context).inflate(R.layout.film_card,parent,false))
    }

    override fun onBindViewHolder(holder: FilmHolder, position: Int) {
        holder.bind(films[position])
    }

    override fun getItemCount(): Int {
        return films.size
    }

    inner class FilmHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(film: MovieListResultObject) {
            itemView.setOnClickListener {
                onItemViewClickListener?.onItemClick(film)
            }
            itemView.findViewById<TextView>(R.id.textViewFilmName).text=film.title
            itemView.findViewById<TextView>(R.id.textViewFilmYear).text = "Рейтинг:"+film?.vote_average?.toString()
            Picasso.get().load("https://image.tmdb.org/t/p/w342/${film.poster_path}?language-ru").into(itemView.findViewById(R.id.imageViewCard) as ImageView)

        }

    }
}