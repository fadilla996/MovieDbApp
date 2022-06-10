package id.iglo.moviedb.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import id.iglo.api_service.service.genre.GenreService
import id.iglo.api_service.service.movie.DiscoverMoviesService
import id.iglo.api_service.service.movie.GetMovieDetailsService
import id.iglo.api_service.service.movie.GetMovieReviewsService
import id.iglo.api_service.service.movie.GetMovieVideosService
import id.iglo.api_service.usecase.genre.GenreUseCase
import id.iglo.api_service.usecase.movie.DiscoverMoviesPagingUseCase
import id.iglo.api_service.usecase.movie.GetMovieDetailsUseCase
import id.iglo.api_service.usecase.movie.GetMovieReviewsPagingUseCase
import id.iglo.api_service.usecase.movie.GetMovieVideosUseCase

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun provideGenreUseCase(genreService: GenreService) = GenreUseCase(genreService)

    @Provides
    fun provideDiscoverMoviesPagingUseCase(discoverMoviesService: DiscoverMoviesService) = DiscoverMoviesPagingUseCase(discoverMoviesService)

    @Provides
    fun provideMovieDetailsUseCase(movieDetailsService: GetMovieDetailsService) = GetMovieDetailsUseCase(movieDetailsService)

    @Provides
    fun provideMovieReviewUseCase(movieReviewsService: GetMovieReviewsService) = GetMovieReviewsPagingUseCase(movieReviewsService)

    @Provides
    fun provideMovieVideoUseCase(movieVideosService: GetMovieVideosService) = GetMovieVideosUseCase(movieVideosService)
}