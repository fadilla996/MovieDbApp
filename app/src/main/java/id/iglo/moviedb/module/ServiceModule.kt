package id.iglo.moviedb.module

import android.content.Context
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.iglo.api_service.RetrofitClient
import id.iglo.api_service.service.genre.GenreService
import id.iglo.api_service.service.movie.DiscoverMoviesService
import id.iglo.api_service.service.movie.GetMovieDetailsService
import id.iglo.api_service.service.movie.GetMovieReviewsService
import id.iglo.api_service.service.movie.GetMovieVideosService
import id.iglo.api_service.usecase.movie.GetMovieReviewsPagingUseCase
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    @Singleton
    fun provideRetrofit(@ApplicationContext context : Context) = RetrofitClient.getClient(context)

    @Provides
    @Singleton
    fun provideGenreService(retrofit: Retrofit) : GenreService = retrofit.create(GenreService::class.java)

    @Provides
    @Singleton
    fun provideDiscoverMovieService(retrofit: Retrofit) : DiscoverMoviesService = retrofit.create(DiscoverMoviesService::class.java)

    @Provides
    @Singleton
    fun provideMovieDetailService(retrofit: Retrofit) : GetMovieDetailsService = retrofit.create(GetMovieDetailsService::class.java)

    @Provides
    @Singleton
    fun provideMovieReviewService(retrofit: Retrofit) : GetMovieReviewsService = retrofit.create(GetMovieReviewsService::class.java)

    @Provides
    @Singleton
    fun provideMovieVideoService(retrofit: Retrofit) : GetMovieVideosService = retrofit.create(GetMovieVideosService::class.java)

}