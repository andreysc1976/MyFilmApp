package ru.a_party.myfilmapp.view

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import ru.a_party.myfilmapp.R
import ru.a_party.myfilmapp.databinding.MainFragmentBinding
import ru.a_party.myfilmapp.model.*
import ru.a_party.myfilmapp.viewmodel.MainViewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: SectionAdapter
    private var _binding: MainFragmentBinding? = null
    private val binding get()=_binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view =  inflater.inflate(R.layout.main_fragment, container, false)
        _binding= MainFragmentBinding.bind(view)
        return binding.root
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        adapter = SectionAdapter(object : OnItemViewClickListener{
            override fun onItemClick(film: MovieListResultObject) {
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
        val rv = binding.sectionRecylerView
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(view.context,LinearLayoutManager.VERTICAL,false)

        viewModel.liveData.observe(viewLifecycleOwner) {
            when(it) {
                is LoadState.Loading -> {
                    binding.loadingLayout.visibility = View.VISIBLE
                    binding.sectionRecylerView.visibility = View.INVISIBLE
                }
                is LoadState.Success-> {
                    binding.loadingLayout.visibility = View.INVISIBLE
                    binding.sectionRecylerView.visibility = View.VISIBLE
                    adapter.sections = it.data
                    adapter.notifyDataSetChanged();
                }
            }
        }
        viewModel.getSectionData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    interface OnItemViewClickListener{
        fun onItemClick(film: MovieListResultObject)
    }

}