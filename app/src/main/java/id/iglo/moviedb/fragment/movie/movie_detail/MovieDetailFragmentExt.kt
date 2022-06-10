package id.iglo.moviedb.fragment.movie.movie_detail

import android.util.Log
import android.view.View
import androidx.paging.LoadState
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragmentX
import id.iglo.moviedb.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun MovieDetailFragment.observeLiveData() {
    binding.recycler.adapter = adapter.withLoadStateFooter(loadStateAdapter)
    vm.getDetailAndReview(movieDetailArgs.movie)
    vm.loadVideo(movieDetailArgs.movie)
    vm.movieDetailData.observe(this) {
        binding.data = it.data
    }
    vm.movieReviewData.observe(this){
        CoroutineScope(Dispatchers.Main).launch {
            adapter.submitData(it)
        }
    }

    vm.movieVideoData.observe(this) {
        val youtubeFragment = YouTubePlayerSupportFragmentX.newInstance()
        with(parentFragmentManager){
            beginTransaction().apply {
                add(R.id.video_trailer, youtubeFragment)
                commit()
            }
            youtubeFragment.initialize(
                "AIzaSyCPx1H6RyEmr1smab8pkQduF8v5k85A4vg",
                object : YouTubePlayerSupportFragmentX.OnInitializedListener(){
                    override fun onInitializationSuccess(
                        p0: YouTubePlayer.Provider?,
                        p1: YouTubePlayer?,
                        p2: Boolean
                    ) {
                        p1?.cueVideo(it)
                    }

                    override fun onInitializationFailure(
                        p0: YouTubePlayer.Provider?,
                        p1: YouTubeInitializationResult?
                    ) {
                        Log.e("YoutubePlayer", "error ${p1?.name}")
                    }
                }
            )
        }
    }

    adapter.addLoadStateListener {
        if(it.refresh is LoadState.Error && adapter.itemCount == 0){
            binding.layoutMovieDetail.visibility = View.GONE
            binding.retryButton2.visibility = View.VISIBLE
            binding.recycler.visibility = View.GONE
            binding.loadingButton.visibility = View.GONE
            binding.retryButton2.setOnClickListener{
                vm.loadVideo(movieDetailArgs.movie)
                vm.getDetailAndReview(movieDetailArgs.movie)
            }
        } else if (it.refresh is LoadState.Loading && adapter.itemCount == 0) {
            binding.retryButton2.visibility = View.GONE
            binding.layoutMovieDetail.visibility = View.GONE
            binding.recycler.visibility = View.GONE
            binding.loadingButton.visibility = View.VISIBLE
        } else if (it.refresh is LoadState.NotLoading) {
            binding.layoutMovieDetail.visibility = View.VISIBLE
            binding.retryButton2.visibility = View.GONE
            binding.loadingButton.visibility = View.GONE
            binding.recycler.visibility = View.VISIBLE
        }
    }
}