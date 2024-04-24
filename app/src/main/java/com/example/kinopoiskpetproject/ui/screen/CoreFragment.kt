package com.example.kinopoiskpetproject.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.kinopoiskpetproject.MyApp
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
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCoreBinding.inflate(inflater,container,false)
        val recycler:RecyclerView = binding.filmlist
        val list:MutableList<Film> = mutableListOf()
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
        return binding.root
    }

    override fun onItemClick(item: Int) {
        Toast.makeText(context, item.toString(), Toast.LENGTH_LONG).show()
    }

    override fun onLongItemClick(item: Int) {
        Toast.makeText(getContext(), "Фильм добавлен в избранное", Toast.LENGTH_LONG).show();
    }
}