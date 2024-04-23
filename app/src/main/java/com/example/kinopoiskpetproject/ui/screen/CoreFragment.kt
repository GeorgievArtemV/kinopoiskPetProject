package com.example.kinopoiskpetproject.ui.screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kinopoiskpetproject.MyApp
import com.example.kinopoiskpetproject.databinding.FragmentCoreBinding
import com.example.kinopoiskpetproject.model.FilmList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CoreFragment : Fragment() {
    private lateinit var binding: FragmentCoreBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCoreBinding.inflate(inflater,container,false)
        val call: Call<FilmList> = MyApp().configureRetrofit().getFilms()
        call.enqueue(object :Callback<FilmList> {
           override fun onResponse(call: Call<FilmList>, response: Response<FilmList>) {
               println(response.body()!!.items.size)
           }

           override fun onFailure(call: Call<FilmList>, t: Throwable) {
               TODO("Not yet implemented")
           }

       })
        /*call.enqueue(object : Callback<ListFilms?> {
            override fun onResponse(call: Call<ListFilms?>?, response: Response<ListFilms?>) {
                list.addAll(response.body().getList())
                filmAdapter = FilmAdapter(list, this@Core, this@Core)
                recyclerView.setAdapter(filmAdapter)
                binding.loadingProgressBar.visibility = View.INVISIBLE
                binding.filmlist.visibility = View.VISIBLE
                binding.layoutNoInternet.visibility = View.INVISIBLE
            }

            override fun onFailure(
                call: Call<ListFilms?>,
                t: Throwable
            ) {      //кэширование, обработка изменения ориентации
                Log.e("TAG", t.message!!)
                binding.loadingProgressBar.visibility = View.INVISIBLE
                binding.layoutNoInternet.visibility = View.VISIBLE
                call.clone().enqueue(this)
            }
        })*/
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }
}