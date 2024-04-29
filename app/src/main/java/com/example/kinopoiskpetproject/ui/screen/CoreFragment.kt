package com.example.kinopoiskpetproject.ui.screen

import android.os.Bundle
import android.telecom.Call.Details
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.kinopoiskpetproject.MyApp
import com.example.kinopoiskpetproject.R
import com.example.kinopoiskpetproject.databinding.FragmentCoreBinding
import com.example.kinopoiskpetproject.model.Film
import com.example.kinopoiskpetproject.ui.utils.OnItemClick
import com.example.kinopoiskpetproject.ui.utils.OnLongItemClick
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class CoreFragment : Fragment(), OnItemClick,OnLongItemClick {
    private lateinit var binding: FragmentCoreBinding
    val list:MutableList<Film> = mutableListOf()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCoreBinding.inflate(inflater,container,false)
        val recycler:RecyclerView = binding.filmlist
        var filmAdapter: FilmAdapter?
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            val result = MyApp().configureRetrofit().getFilms()
            val jobGetting:Job = launch{
                list.addAll(result.body()!!.items)
            }
            jobGetting.start()
            val jobSetting:Job = launch(Dispatchers.Main) {
                filmAdapter = FilmAdapter(list,this@CoreFragment,this@CoreFragment)
                binding.loadingProgressBar.visibility = View.INVISIBLE
                binding.filmlist.visibility = View.VISIBLE
                recycler.adapter = filmAdapter
            }
            jobSetting.join()
        }
        binding.favorite.setOnClickListener(View.OnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.placeHolder, FavoriteFragment())
                .addToBackStack("first")
                .commit()
            Toast.makeText(context, "Фильм добавлен в избранное", Toast.LENGTH_LONG).show()
        })
        return binding.root
    }

    override fun onItemClick(item: Int) {
        val bundle = Bundle()
        bundle.putInt("FilmId", list[item].kinopoiskId)
        val details = DetailsFragment()
        details.setArguments(bundle)
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.placeHolder, details)
            .addToBackStack("first")
            .commit()
    }


    override fun onLongItemClick(item: Int) {
        Toast.makeText(context, "Фильм добавлен в избранное", Toast.LENGTH_LONG).show()
    }
}