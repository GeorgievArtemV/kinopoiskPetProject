package com.example.kinopoiskpetproject.ui.screen

import dagger.assisted.AssistedFactory

@AssistedFactory
interface DetailsViewModelFactory {
    fun create(id: Int): DetailsViewModel
}