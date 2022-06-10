package id.iglo.moviedb.fragment.movie.movie_review

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import id.iglo.moviedb.databinding.DiscoverMovieItemStateLayoutBinding

class MovieReviewPagingStateAdapter() :
    LoadStateAdapter<MovieReviewStateViewHolder>(){
    override fun onBindViewHolder(holder: MovieReviewStateViewHolder, loadState: LoadState) =
        holder.bind(loadState)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): MovieReviewStateViewHolder = MovieReviewStateViewHolder(
        DiscoverMovieItemStateLayoutBinding.inflate(
            LayoutInflater.from(
            parent.context
        ), parent, false)).apply { this.bind(loadState) }
}

class MovieReviewStateViewHolder (private val binding : DiscoverMovieItemStateLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(loadState: LoadState) {
        when (loadState) {
            is LoadState.Error -> {
                binding.errorContainer.visibility = View.GONE
                binding.loadingContainer.visibility = View.VISIBLE
            }
            is LoadState.Loading -> {
                binding.loadingContainer.visibility = View.VISIBLE
                binding.errorContainer.visibility = View.GONE
            }
            is LoadState.NotLoading -> {
                binding.loadingContainer.visibility = View.GONE
                binding.errorContainer.visibility = View.GONE
            }
        }
    }
}