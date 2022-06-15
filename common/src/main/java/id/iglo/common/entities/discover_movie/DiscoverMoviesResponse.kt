package id.iglo.common.entities.discover_movie


import com.google.gson.annotations.SerializedName

data class DiscoverMoviesResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MovieResult>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)