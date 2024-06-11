package com.example.kinopoiskpetproject.ui.screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kinopoiskpetproject.di.modules.db.DataAccessObject
import com.example.kinopoiskpetproject.di.modules.network.FilmAPI
import com.example.kinopoiskpetproject.model.Film
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = DetailsViewModelFactory::class)
class DetailsViewModel  @AssistedInject constructor(
    @Assisted var film: Film,
    private val filmAPI: FilmAPI,
    private val dataAccessObject: DataAccessObject
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
            val result = filmAPI.getFilmDetails(film.kinopoiskId)
            posterLiveData.postValue(result!!.body()!!.posterUrl)
            nameLiveData.postValue(result.body()!!.nameRu)
            descLiveData.postValue(result.body()!!.description)
            booleanLiveData.postValue(true)
        }
    }
    fun insertData () {
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            dataAccessObject.insert(film)
        }
    }
}