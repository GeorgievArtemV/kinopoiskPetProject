package com.example.kinopoiskpetproject.ui.screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kinopoiskpetproject.di.modules.network.FilmAPI
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = DetailsViewModelFactory::class)
class DetailsViewModel  @AssistedInject constructor(
    @Assisted var id:Int,
    private val filmAPI: FilmAPI
    ) :ViewModel() {

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
            val result = filmAPI.getFilmDetails(id)
            posterLiveData.postValue(result!!.body()!!.posterUrl)
            nameLiveData.postValue(result.body()!!.nameRu)
            descLiveData.postValue(result.body()!!.description)
            booleanLiveData.postValue(true)
        }
    }
}