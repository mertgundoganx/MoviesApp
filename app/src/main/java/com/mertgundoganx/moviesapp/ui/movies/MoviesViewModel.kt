package com.mertgundoganx.moviesapp.ui.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mertgundoganx.moviesapp.data.api.MoviesResponseService
import com.mertgundoganx.moviesapp.data.repository.MoviesRepository
import com.mertgundoganx.moviesapp.utils.Resource
import kotlinx.coroutines.Dispatchers

class MoviesViewModel : ViewModel() {
    private val moviesRepository = MoviesRepository(MoviesResponseService.service)
    private val _currentPage = MutableLiveData(1)
    val currentPage get() = _currentPage

    fun getMovies() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(moviesRepository.getMovies(currentPage.value!!)))
        } catch (e: Exception) {
            emit(Resource.error(null, e.toString()))
        }
    }

    fun nextPage() {
        _currentPage.postValue(currentPage.value!! + 1)
    }

    fun backPage() {
        _currentPage.postValue(currentPage.value!! - 1)
    }

}