package id.iglo.moviedb.fragment.movie.movie_review

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.iglo.common.entity.movie_reviews.Result
import id.iglo.moviedb.databinding.MovieReviewItemLayoutBinding

class MovieReviewPagingAdapter: PagingDataAdapter<Result, MovieReviewViewHolder>(differ) {
    companion object{
        val differ = object: DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return true
            }
        }
    }

    override fun onBindViewHolder(holder: MovieReviewViewHolder, position: Int) = holder.bind(getItem(position))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieReviewViewHolder(
        MovieReviewItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

}

class MovieReviewViewHolder(
    private val binding: MovieReviewItemLayoutBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(review : Result?) {
        binding.data = review
    }
}