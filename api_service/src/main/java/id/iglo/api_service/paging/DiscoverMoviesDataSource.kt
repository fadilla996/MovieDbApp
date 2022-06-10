package id.iglo.api_service.paging


import id.iglo.common.entity.discover_movie.MovieResult
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import id.iglo.api_service.service.movie.DiscoverMoviesService

class DiscoverMoviesDataSource (
    private val discoverMoviesService: DiscoverMoviesService,
    private val genres: String) : PagingSource<Int, MovieResult>(){

    override fun getRefreshKey(state: PagingState<Int, MovieResult>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResult> {
        val page = params.key ?: 1
        val prevPage = if (page == 1) null else - 1

        try {
            // params.loadSize
            val data = discoverMoviesService.discoverMoviesByGenre(
                genres, page
            )

            if (data.isSuccessful) {
                data.body()?.let {
                    val nextPage = if (it.results.isEmpty()) null else page + 1
                    return LoadResult.Page(data = it.results, prevPage, nextPage)
                } ?: kotlin.run {
                    return LoadResult.Page(arrayListOf(), prevPage, null)
                }
            } else {
                return LoadResult.Error(Exception("Error Backend : ${data.code()}"))
            }

        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    companion object DiscoverMoviesPager {
        fun createPager(
            pageSize: Int,
            discoverMoviesService: DiscoverMoviesService,
            genres: String
        ): Pager<Int, MovieResult> = Pager(
            config = PagingConfig(pageSize),
            pagingSourceFactory = {
                DiscoverMoviesDataSource(discoverMoviesService, genres)
            }
        )
    }
}