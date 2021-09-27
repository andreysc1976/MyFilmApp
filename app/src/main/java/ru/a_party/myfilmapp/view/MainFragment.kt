package ru.a_party.myfilmapp.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.a_party.myfilmapp.R
import ru.a_party.myfilmapp.model.Film
import ru.a_party.myfilmapp.model.LoadDataImpl
import ru.a_party.myfilmapp.model.LoadState
import ru.a_party.myfilmapp.model.Section
import ru.a_party.myfilmapp.viewmodel.MainViewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: SectionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = SectionAdapter(object : OnItemViewClickListener{
            override fun onItemClick(film: Film) {
                val manager = activity?.supportFragmentManager;
                if (manager!=null){
                    val bundle = Bundle();
                    bundle.putParcelable(FilmFragment.BUNDLE_EXTRA,film)
                    manager.beginTransaction()
                        .replace(R.id.container,FilmFragment.newInstance(bundle))
                        .addToBackStack("")
                        .commitAllowingStateLoss()
                }
            }

        } );
        val rv = view.findViewById<RecyclerView>(R.id.sectionRecylerView)
        adapter.sections=LoadDataImpl().loadFromServer();
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(view.context,LinearLayoutManager.VERTICAL,false)


        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

                // TODO: Use the ViewModel
    }

    interface OnItemViewClickListener{
        fun onItemClick(film: Film)
    }

}