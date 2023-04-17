package com.wahyurhy.androidtvleanback.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wahyurhy.androidtvleanback.api.Response
import com.wahyurhy.androidtvleanback.api.TmdbRepo
import com.wahyurhy.androidtvleanback.model.response.cast.CastResponse
import com.wahyurhy.androidtvleanback.model.response.detail.DetailResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(val repo: TmdbRepo, id: Int) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getMovieDetails(id)
            repo.getMovieCast(id)
        }
    }

    val movieDetails: LiveData<Response<DetailResponse>>
        get() = repo.movieDetail

    val castDetails: LiveData<Response<CastResponse>>
        get() = repo.castDetail

}