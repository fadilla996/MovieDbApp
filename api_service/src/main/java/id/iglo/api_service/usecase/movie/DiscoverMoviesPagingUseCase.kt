package id.iglo.api_service.usecase.movie

import id.iglo.api_service.Constants
import id.iglo.api_service.paging.DiscoverMoviesDataSource
import id.iglo.api_service.service.movie.DiscoverMoviesService

class DiscoverMoviesPagingUseCase(
    private val discoverMoviesService: DiscoverMoviesService
) {
    operator fun invoke(genre: String) =
        DiscoverMoviesDataSource.createPager(
            Constants.DEFAULT_PAGE_SIZE,
            discoverMoviesService,
            genre
        )
}
