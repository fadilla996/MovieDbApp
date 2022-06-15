package id.iglo.moviedb.fragment.movie.movie_detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import id.iglo.common.ui.BaseFragment
import id.iglo.moviedb.R
import id.iglo.moviedb.fragment.movie.movie_review.MovieReviewPagingAdapter
import id.iglo.moviedb.fragment.movie.movie_review.MovieReviewPagingStateAdapter
import id.iglo.moviedb.view_model.movie.MovieDetailViewModel
import id.iglo.moviedb.databinding.LayoutMovieDetailFragmentBinding

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<MovieDetailViewModel, LayoutMovieDetailFragmentBinding>(){
    override val vm: MovieDetailViewModel by viewModels()
    override val layoutResourceId: Int = R.layout.layout_movie_detail_fragment

    val adapter = MovieReviewPagingAdapter()
    val loadStateAdapter = MovieReviewPagingStateAdapter()
    val movieDetailArgs : MovieDetailFragmentArgs by navArgs()

    override fun initBinding(binding: LayoutMovieDetailFragmentBinding) {
        super.initBinding(binding)
        observeLiveData()
    }
}