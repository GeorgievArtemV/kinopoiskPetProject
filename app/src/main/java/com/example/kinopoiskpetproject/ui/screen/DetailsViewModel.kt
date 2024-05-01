package com.example.kinopoiskpetproject.ui.screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kinopoiskpetproject.MyApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel(val id:Int):ViewModel() {
    val posterLiveData = MutableLiveData<String?>()
    val nameLiveData = MutableLiveData<String?>()
    val descLiveData = MutableLiveData<String?>()
    val booleanLiveData = MutableLiveData<Boolean>()
    init {
        getDetails()
    }
    private fun getDetails(){
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            val result = MyApp().configureRetrofit().getFilmDetails(id)
            posterLiveData.postValue(result!!.body()!!.posterUrl)
            nameLiveData.postValue(result.body()!!.nameRu)
            descLiveData.postValue(result.body()!!.description)
            booleanLiveData.postValue(true)
        }
    }
}