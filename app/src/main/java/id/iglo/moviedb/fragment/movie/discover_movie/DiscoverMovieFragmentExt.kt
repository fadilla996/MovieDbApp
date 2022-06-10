package id.iglo.moviedb.fragment.movie.discover_movie

import android.view.View
import androidx.paging.LoadState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun DiscoverMovieFragment.observeLiveData() {
    binding.recycler.adapter = adapter.withLoadStateFooter(loadStateAdapter)
    vm.discoverMovie(discoverArgs.genre)
    vm.discoverData.observe(this) {
        binding.loadingContainer.visibility = android.view.View.GONE
        binding.recycler.visibility = android.view.View.VISIBLE
        CoroutineScope(kotlinx.coroutines.Dispatchers.Main).launch {
            adapter.submitData(it)
        }
    }

    adapter.addLoadStateListener {
        if(it.refresh is LoadState.Error && adapter.itemCount == 0){
            binding.retryButton.visibility = android.view.View.VISIBLE
            binding.recycler.visibility = android.view.View.GONE
            binding.loadingContainer.visibility = android.view.View.GONE
            binding.retryButton.setOnClickListener{
                adapter.retry()
            }
        } else if (it.refresh is LoadState.Loading && adapter.itemCount == 0) {
            binding.retryButton.visibility = android.view.View.GONE
            binding.recycler.visibility = android.view.View.GONE
            binding.loadingContainer.visibility = android.view.View.VISIBLE
        } else if (it.refresh is LoadState.NotLoading) {
            binding.retryButton.visibility = android.view.View.GONE
            binding.loadingContainer.visibility = android.view.View.GONE
            binding.recycler.visibility = android.view.View.VISIBLE
        }
    }
}