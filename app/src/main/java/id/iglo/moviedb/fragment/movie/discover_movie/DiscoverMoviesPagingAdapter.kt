package id.iglo.moviedb.fragment.movie.discover_movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.iglo.common.entities.discover_movie.MovieResult
import id.iglo.moviedb.databinding.LayoutDiscoverMovieItemBinding

class DiscoverMoviesPagingAdapter(
    val selectMovie : (MovieResult) -> Unit
) : PagingDataAdapter<MovieResult, DiscoverMovieViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverMovieViewHolder =
        DiscoverMovieViewHolder(
            LayoutDiscoverMovieItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))

    override fun onBindViewHolder(holder: DiscoverMovieViewHolder, position: Int) {
        val data = getItem(position)
        holder.binding.data = data
        holder.binding.cardView.setOnClickListener {
            data?.let { it1 ->
                selectMovie(it1)
            }
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<MovieResult>() {
            override fun areItemsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean {
               return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean {
                return oldItem == newItem
            }
        }
    }

}

class DiscoverMovieViewHolder (val binding : LayoutDiscoverMovieItemBinding) : RecyclerView.ViewHolder(binding.root) {
}