package id.iglo.moviedb.fragment.movie.discover_movie

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import id.iglo.common.ui.BaseFragment
import id.iglo.moviedb.R
import id.iglo.moviedb.databinding.LayoutDiscoverMovieFragmentBinding
import id.iglo.moviedb.view_model.movie.DiscoverMovieViewModel

@AndroidEntryPoint
class DiscoverMovieFragment : BaseFragment<DiscoverMovieViewModel, LayoutDiscoverMovieFragmentBinding>() {
    override val vm: DiscoverMovieViewModel by viewModels()
    override val layoutResourceId: Int = R.layout.layout_discover_movie_fragment
    val adapter = DiscoverMoviesPagingAdapter{
        findNavController().navigate(DiscoverMovieFragmentDirections.toMovieDetail(it.id))
    }
    val discoverArgs : DiscoverMovieFragmentArgs by navArgs()
    val loadStateAdapter = DiscoverMoviePagingStateAdapter()

    override fun initBinding(binding: LayoutDiscoverMovieFragmentBinding) {
        super.initBinding(binding)
        observeLiveData()
    }
}