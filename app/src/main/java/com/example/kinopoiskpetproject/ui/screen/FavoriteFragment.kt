package com.example.kinopoiskpetproject.ui.screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kinopoiskpetproject.databinding.FragmentFavoriteBinding
import com.example.kinopoiskpetproject.ui.utils.FavoriteAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel: FavoriteViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val recycler: RecyclerView = binding.favoritefilmlist
        var favoriteAdapter: FavoriteAdapter?
        viewModel.setLiveData.observe(viewLifecycleOwner){
            favoriteAdapter = FavoriteAdapter(it)
            Log.d("TAGA", it.toString())
            recycler.layoutManager = GridLayoutManager(context,2)
            recycler.adapter = favoriteAdapter
        }

        return binding.root
    }
}