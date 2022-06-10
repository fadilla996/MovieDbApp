package id.iglo.api_service.usecase.movie

import id.iglo.api_service.Constants
import id.iglo.api_service.paging.MovieReviewsPager
import id.iglo.api_service.service.movie.GetMovieReviewsService

class GetMovieReviewsPagingUseCase(private val getMovieReviewsService: GetMovieReviewsService) {
    operator fun invoke(movieId : Int) = MovieReviewsPager.createPager(Constants.DEFAULT_PAGE_SIZE, getMovieReviewsService, movieId).flow
}