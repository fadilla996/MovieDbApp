package id.iglo.common.entity.genre


import com.google.gson.annotations.SerializedName

data class GenreListResponse(
    @SerializedName("genres")
    val genres: List<Genre>
)