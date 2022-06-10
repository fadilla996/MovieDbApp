package id.iglo.api_service.service.movie

import id.iglo.api_service.Constants
import id.iglo.common.entity.discover_movie.DiscoverMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DiscoverMoviesService {
    @GET("discover/movie")
    suspend fun discoverMoviesByGenre(
        @Query("with_genres") genre : String,
        @Query("page") page : Int = 1,
        @Query("api_key") apiKey : String = Constants.API_KEY_V3
    ) : Response<DiscoverMoviesResponse>
}