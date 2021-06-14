package com.abhijeet.movies

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.MergeAdapter
import com.abhijeet.commons.extensions.appContext
import com.abhijeet.commons.extensions.observe
import com.abhijeet.commons.extensions.showSnackbar
import com.abhijeet.commons.ui.GridViewItemDecoration
import com.abhijeet.core.data.DataState
import com.abhijeet.core.data.entities.Movie
import com.abhijeet.core.di.provider.CoreComponentProvider
import com.abhijeet.movies.R
import com.abhijeet.movies.adapter.MoviesAdapter
import com.abhijeet.movies.adapter.StatusFooterAdapter
import com.abhijeet.movies.databinding.MoviesFragmentMoviesBinding
import com.abhijeet.movies.di.DaggerMoviesComponent
import com.abhijeet.movies.di.MoviesModule
import javax.inject.Inject

class MoviesFragment : Fragment(R.layout.movies_fragment_movies) {

    @Inject
    lateinit var viewModel: MoviesViewModel

    private lateinit var binding: MoviesFragmentMoviesBinding

    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var statusFooterAdapter: StatusFooterAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setupDependencyInjection()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MoviesFragmentMoviesBinding.bind(view)
        setupRecyclerView()
        observe(viewModel.state, ::onViewStateChanged)
        observe(viewModel.data, ::onViewDataChanged)
        observe(viewModel.event, ::onViewEvent)
    }

    private fun setupRecyclerView() {
        moviesAdapter = MoviesAdapter(viewModel)
        statusFooterAdapter = StatusFooterAdapter(viewModel)
        with(binding.moviesList) {
            layoutManager = GridLayoutManager(requireContext(), 2)
            addItemDecoration(
                GridViewItemDecoration(
                    spanCount = resources.getInteger(R.integer.movies_movie_list_span_count),
                    spacing = resources.getDimensionPixelOffset(R.dimen.movies_movie_item_spacing)
                )
            )
            adapter = MergeAdapter(moviesAdapter, statusFooterAdapter)
        }
    }

    private fun onViewStateChanged(viewState: MoviesViewState) {
        statusFooterAdapter.viewState = viewState
        when (val dataState = viewState.dataState) {
            is DataState.Error -> {
                showSnackbar(dataState.message)
            }
            else -> {
                // do nothing
            }
        }
    }

    private fun onViewDataChanged(movies: PagedList<Movie>) {
        moviesAdapter.submitList(movies)
    }

    private fun onViewEvent(viewEvent: MoviesEvent) {
        when (viewEvent) {
            is MoviesEvent.OpenMovieDetail -> {
                findNavController().navigate(
                    MoviesFragmentDirections.actionMoviesFragmentToMovieDetailFragment(
                        viewEvent.movieId, viewEvent.movieTitle
                    )
                )
            }
        }
    }

    private fun setupDependencyInjection() {
        DaggerMoviesComponent
            .builder()
            .coreComponent((appContext as CoreComponentProvider).provideCoreComponent())
            .moviesModule(MoviesModule(this))
            .build()
            .inject(this)
    }
}