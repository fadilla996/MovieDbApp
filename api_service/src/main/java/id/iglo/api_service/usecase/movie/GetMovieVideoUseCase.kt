package id.iglo.api_service.usecase.movie

import id.iglo.api_service.service.movie.GetMovieVideosService
import id.iglo.common.base_response.AppResponse
import id.iglo.common.entities.movie_videos.MovieVideosResponse
import kotlinx.coroutines.flow.flow

class GetMovieVideosUseCase (private val getMovieVideosService: GetMovieVideosService){
    operator fun invoke(movieId: Int) = flow {
        try{
        emit(AppResponse.loading())
        val data = getMovieVideosService.getMovieVideos(movieId)
        if (data.isSuccessful) {
            data.body()?.let {
                emit(AppResponse.success(it))
            } ?: run {
                emit(AppResponse.errorBackEnd<MovieVideosResponse>(data.code(), data.errorBody()))
            }

        } else {
            emit(AppResponse.errorBackEnd(data.code(), data.errorBody()))
        }
    } catch (e : Exception) {
        emit(AppResponse.errorSystem(e))
    }
}

}