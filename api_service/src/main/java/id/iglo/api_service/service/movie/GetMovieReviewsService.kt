package id.iglo.api_service.service.movie

import id.iglo.api_service.Constants
import id.iglo.common.entity.movie_reviews.MovieReviewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetMovieReviewsService {
    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews (
        @Path("movie_id") movieId : Int,
        @Query("api_key") apiKey : String = Constants.API_KEY_V3,
        @Query("page") page : Int = 1
    ) : Response<MovieReviewsResponse>
}