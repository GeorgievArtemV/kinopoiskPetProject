package com.example.kinopoiskpetproject.ui.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.kinopoiskpetproject.MyApp
import com.example.kinopoiskpetproject.R
import com.example.kinopoiskpetproject.database.AppDataBase
import com.example.kinopoiskpetproject.databinding.FragmentFavoriteBinding


class FavoriteFragment : Fragment() {
    lateinit var binding:FragmentFavoriteBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFavoriteBinding.inflate(inflater,container,false)

        return binding.root
    }
}