package id.iglo.api_service.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import id.iglo.common.entities.movie_reviews.Result
import id.iglo.api_service.service.movie.GetMovieReviewsService

class MovieReviewsDataSource(
    private val movieReviewsService: GetMovieReviewsService,
    private val movieId: Int
) : PagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val page = params.key ?: 1
        val prevPage = if (page == 1) null else -1

        try {
            val data = movieReviewsService.getMovieReviews(movieId, page = page)
            if (data.isSuccessful) {
                data.body()?.let {
                    val nextPage = if (it.results.isEmpty()) null else page + 1
                    return LoadResult.Page(data = it.results, prevPage, nextPage)
                } ?: run {
                    return LoadResult.Page(arrayListOf(), prevPage, null)
                }
            } else {
                return LoadResult.Error(Exception("Error Backend : ${data.code()}"))
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}

object MovieReviewsPager {
    fun createPager(pageSize : Int,
                    movieReviewsService: GetMovieReviewsService,
                    movieId: Int) : Pager<Int, Result> = Pager (
        config = PagingConfig(pageSize),
        pagingSourceFactory = {
            MovieReviewsDataSource(movieReviewsService, movieId)
        }
    )
}