package com.example.kinopoiskpetproject.ui.screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kinopoiskpetproject.di.modules.db.DataAccessObject
import com.example.kinopoiskpetproject.model.Film
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val dataAccessObject: DataAccessObject) : ViewModel() {
    val setLiveData = MutableLiveData<List<Film>>()
    init {
        setData()
    }
    private fun setData() {
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            setLiveData.postValue(dataAccessObject.getAll())
        }
    }
}
