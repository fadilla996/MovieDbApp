package id.iglo.moviedb.view_model.movie

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.iglo.api_service.usecase.movie.GetMovieDetailsUseCase
import id.iglo.api_service.usecase.movie.GetMovieReviewsPagingUseCase
import id.iglo.api_service.usecase.movie.GetMovieVideosUseCase
import id.iglo.common.base_response.AppResponse
import id.iglo.common.entity.discover_movie.MovieResult
import id.iglo.common.entity.movie_details.MovieDetailsResponse
import id.iglo.common.entity.movie_reviews.Result
import id.iglo.common.ext.SingleLiveEvent
import id.iglo.common.ui.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    application: Application,
    val movieDetailsUseCase: GetMovieDetailsUseCase,
    val movieReviewsPagingUseCase: GetMovieReviewsPagingUseCase,
    val movieVideosUseCase: GetMovieVideosUseCase
) : BaseViewModel(application) {

    val movieDetailData = MutableLiveData<AppResponse<MovieDetailsResponse>>()
    val movieReviewData = MutableLiveData<PagingData<Result>>()
    val movieVideoData = SingleLiveEvent<String?>()

    fun getDetailAndReview(movie : Int) {
        viewModelScope.launch {
            movieDetailsUseCase(movie).collect {
                movieDetailData.postValue(it)
            }
            movieReviewsPagingUseCase.invoke(movie).collect {
                movieReviewData.postValue(it)
            }
        }
    }

    fun loadVideo(videoId : Int) {
        viewModelScope.launch {
            movieVideosUseCase.invoke(videoId).collect {
                it.data?.results?.get(0)?.key.let { it1 ->
                    movieVideoData.postValue(it1)
                }
            }
        }
    }

}