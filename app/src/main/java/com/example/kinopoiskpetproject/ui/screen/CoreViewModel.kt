package com.example.kinopoiskpetproject.ui.screen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kinopoiskpetproject.di.modules.network.FilmAPI
import com.example.kinopoiskpetproject.model.Film
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoreViewModel @Inject constructor(
    private val filmAPI: FilmAPI,
): ViewModel() {
    val listLiveData = MutableLiveData<MutableList<Film>>()
    val booleanLiveData = MutableLiveData<Boolean>()
    init {
        getListAPI()
    }
    fun getListAPI() {
        val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
        throwable.printStackTrace() }
        val scope = CoroutineScope(Dispatchers.IO + coroutineExceptionHandler)
        scope.launch {
            try {
                val result = filmAPI.getFilms()
                listLiveData.postValue(result.body()!!.items)
                booleanLiveData.postValue(true)
                Log.d("TAG", "22")
            } catch (e: Exception){
                booleanLiveData.postValue(false)
                Log.d("TAG", "12")
            }
            Log.d("TAG", "23")
        }
    }
}