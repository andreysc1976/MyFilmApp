package ru.a_party.myfilmapp.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.a_party.myfilmapp.R
import ru.a_party.myfilmapp.model.Film
import ru.a_party.myfilmapp.model.LoadData
import ru.a_party.myfilmapp.model.LoadDataImpl
import ru.a_party.myfilmapp.model.Section

class SectionAdapter(private var onItemViewClickListener: MainFragment.OnItemViewClickListener?):RecyclerView.Adapter<SectionAdapter.SectionHolder>() {

    var sections:List<Section> = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value){
            field = value
            notifyDataSetChanged()
        }

    fun removeListener() {
        onItemViewClickListener = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionHolder {
        return SectionHolder(LayoutInflater.from(parent.context).inflate(R.layout.films_holder,parent,false))
    }

    override fun onBindViewHolder(holder: SectionHolder, position: Int) {
        holder.bind(sections[position])
    }

    override fun getItemCount(): Int {
        return sections.size;
        //return 5
    }

    inner class SectionHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(section: Section) {
            itemView.findViewById<TextView>(R.id.textViewSectionName).text =section.name;
            val localRV = itemView.findViewById<RecyclerView>(R.id.rvFilms)
            val adapter = FilmAdapter(onItemViewClickListener);
            adapter.films = LoadDataImpl().loadFilmsBySection(section)
            localRV.adapter = adapter
            localRV.layoutManager = LinearLayoutManager(this.itemView.context,LinearLayoutManager.HORIZONTAL,false)
        }

    }
}