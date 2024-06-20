package com.example.kinopoiskpetproject.ui.screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.kinopoiskpetproject.R
import com.example.kinopoiskpetproject.databinding.FragmentCoreBinding
import com.example.kinopoiskpetproject.ui.utils.FilmAdapter
import com.example.kinopoiskpetproject.ui.utils.OnItemClick
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoreFragment : Fragment(), OnItemClick {
    private lateinit var binding: FragmentCoreBinding
    private val vm: CoreViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCoreBinding.inflate(inflater,container,false)
        val recycler:RecyclerView = binding.filmlist
        var filmAdapter: FilmAdapter?


        vm.listLiveData.observe(viewLifecycleOwner) {
            filmAdapter = FilmAdapter(it, this@CoreFragment)
            recycler.adapter = filmAdapter
        }
        vm.booleanLiveData.observe(viewLifecycleOwner) {
            if (it == true) {
                binding.layoutNoInternet.visibility = View.INVISIBLE
                binding.loadingProgressBar.visibility = View.INVISIBLE
                binding.filmlist.visibility = View.VISIBLE
                Log.d("TAG", "1")
            } else  {
                binding.loadingProgressBar.visibility = View.INVISIBLE
                binding.layoutNoInternet.visibility = View.VISIBLE
                Log.d("TAG", "2")
            }
        }
        binding.buttonNoInternet.setOnClickListener {
            vm.getListAPI()
        }
        binding.favorite.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.placeHolder, FavoriteFragment()).addToBackStack("first").commit()
        }
        return binding.root
    }

    override fun onItemClick(item: Int) {
        vm.listLiveData.observe(viewLifecycleOwner) {
            val bundle = Bundle()
            bundle.putParcelable("FilmId", it[item])
            val details = DetailsFragment()
            details.setArguments(bundle)
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.placeHolder, details).addToBackStack("first").commit()
        }
    }
}