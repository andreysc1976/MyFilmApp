package ru.a_party.myfilmapp.view

import android.annotation.SuppressLint
import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ru.a_party.myfilmapp.R
import ru.a_party.myfilmapp.model.Film

class FilmAdapter(private var onItemViewClickListener: MainFragment.OnItemViewClickListener?): RecyclerView.Adapter<FilmAdapter.FilmHolder>() {
    var films:List<Film> = listOf()
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
        fun bind(film: Film) {
            itemView.setOnClickListener {
                onItemViewClickListener?.onItemClick(film)
            }
        }

    }
}