package id.iglo.moviedb.fragment.genre

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import dagger.hilt.android.AndroidEntryPoint
import id.iglo.common.ui.BaseFragment
import id.iglo.moviedb.R
import id.iglo.moviedb.databinding.LayoutGenreFragmentBinding
import id.iglo.moviedb.view_model.genre.GenreViewModel

@AndroidEntryPoint
class GenreFragment : BaseFragment<GenreViewModel, LayoutGenreFragmentBinding>() {
    override val vm: GenreViewModel by viewModels()
    override val layoutResourceId: Int = R.layout.layout_genre_fragment
    val adapter = GenreAdapter() {
        vm.selectionTracker?.isSelected(it) ?: false
    }

    override fun initBinding(binding: LayoutGenreFragmentBinding) {
        super.initBinding(binding)
        observeLiveData()
    }
}