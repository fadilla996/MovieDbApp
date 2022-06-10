package id.iglo.api_service.usecase.movie

import id.iglo.api_service.service.movie.GetMovieDetailsService
import id.iglo.common.base_response.AppResponse
import id.iglo.common.entity.movie_details.MovieDetailsResponse
import kotlinx.coroutines.flow.flow

class GetMovieDetailsUseCase (private val getMovieDetailsService: GetMovieDetailsService) {

    operator fun invoke(movieId: Int) = flow {
        try {
            emit(AppResponse.loading())
            val data = getMovieDetailsService.getMovieDetails(movieId)
            if (data.isSuccessful) {
                data.body()?.let {
                    emit(AppResponse.success(it))
                } ?: run {
                    emit(
                        AppResponse.errorBackEnd<MovieDetailsResponse>(data.code(), data.errorBody())
                    )
                }
            } else {
                emit(AppResponse.errorBackEnd(data.code(), data.errorBody()))
            }
        } catch (e : Exception) {
            emit(AppResponse.errorSystem(e))
        }
    }
}