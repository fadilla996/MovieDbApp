package id.iglo.api_service.service.movie

import id.iglo.api_service.Constants
import id.iglo.common.entity.movie_videos.MovieVideosResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetMovieVideosService {
    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(
        @Path("movie_id") movieId : Int,
        @Query("api_key") apiKey : String = Constants.API_KEY_V3
    ) : Response<MovieVideosResponse>
}