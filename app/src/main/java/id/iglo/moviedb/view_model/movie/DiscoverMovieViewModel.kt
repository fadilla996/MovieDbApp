package id.iglo.moviedb.view_model.movie

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.iglo.api_service.usecase.movie.DiscoverMoviesPagingUseCase
import id.iglo.common.entity.discover_movie.MovieResult
import id.iglo.common.ext.SingleLiveEvent
import id.iglo.common.ui.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverMovieViewModel @Inject constructor(application: Application, val discoverMoviesPagingUseCase: DiscoverMoviesPagingUseCase) : BaseViewModel(application) {
    val discoverData = MutableLiveData<PagingData<MovieResult>>()

    fun discoverMovie(genre : String) {
        viewModelScope.launch {
            discoverMoviesPagingUseCase.invoke(
                genre
            ).flow.collect {
                discoverData.postValue(it)
            }
        }
    }
}