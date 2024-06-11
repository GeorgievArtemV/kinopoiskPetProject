package com.example.kinopoiskpetproject.ui.screen

import com.example.kinopoiskpetproject.model.Film
import dagger.assisted.AssistedFactory

@AssistedFactory
interface DetailsViewModelFactory {
    fun create(film: Film): DetailsViewModel
}