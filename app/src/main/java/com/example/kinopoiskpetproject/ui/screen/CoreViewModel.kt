package com.example.kinopoiskpetproject.ui.screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kinopoiskpetproject.MyApp
import com.example.kinopoiskpetproject.model.Film
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoreViewModel: ViewModel() {


    val listLiveData = MutableLiveData<MutableList<Film>>()
    init {
        getListAPI()
    }
    private fun getListAPI() {
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            val result = MyApp().configureRetrofit().getFilms()
            listLiveData.postValue(result.body()!!.items)
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}