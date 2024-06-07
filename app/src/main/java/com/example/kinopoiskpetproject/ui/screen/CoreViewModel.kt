package com.example.kinopoiskpetproject.ui.screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kinopoiskpetproject.di.modules.network.FilmAPI
import com.example.kinopoiskpetproject.model.Film
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoreViewModel @Inject constructor(
    private val filmAPI: FilmAPI
): ViewModel() {
    val listLiveData = MutableLiveData<MutableList<Film>>()
    val booleanLiveData = MutableLiveData<Boolean>()
    init {
        getListAPI()
    }
    private fun getListAPI() {
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            val result = filmAPI.getFilms()
            listLiveData.postValue(result.body()!!.items)
            booleanLiveData.postValue(true)
        }
    }

}