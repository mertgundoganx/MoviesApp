package com.mertgundoganx.moviesapp.ui.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mertgundoganx.moviesapp.data.repository.MoviesResponseRepository
import com.mertgundoganx.moviesapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val moviesResponseRepository: MoviesResponseRepository) : ViewModel() {
    private val _currentPage = MutableLiveData(1)
    val currentPage get() = _currentPage

    fun getMovies() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(moviesResponseRepository.getMovies(currentPage.value!!)))
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