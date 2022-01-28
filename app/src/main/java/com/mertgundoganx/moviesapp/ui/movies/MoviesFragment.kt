package com.mertgundoganx.moviesapp.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.mertgundoganx.moviesapp.ui.movies.adapter.MoviesAdapter
import com.mertgundoganx.moviesapp.data.model.MoviesResponse
import com.mertgundoganx.moviesapp.data.model.Movie
import com.mertgundoganx.moviesapp.databinding.MoviesFragmentBinding
import com.mertgundoganx.moviesapp.utils.Status

class MoviesFragment : Fragment(), MoviesAdapter.OnItemClickListener {

    private val viewModel: MoviesViewModel by viewModels()
    private var _binding: MoviesFragmentBinding? = null
    private val binding get() = _binding!!
    private val moviesAdapter = MoviesAdapter(arrayListOf(), this)
    private lateinit var movieList: MoviesResponse

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MoviesFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
        getLiveData()

    }

    override fun onItemClick(position: Int) {
        val movie: Movie = movieList.results[position]
        goToMovie(movie)
    }

    private fun setAdapter() {
        binding.recylerView.apply {
            layoutManager = GridLayoutManager(this.context, 3)
            setHasFixedSize(true)
            adapter = moviesAdapter
        }
    }

    private fun getLiveData() {
        viewModel.getMovies().observe(viewLifecycleOwner) {
            it?.let {
                when (it.status) {
                    Status.SUCCESS -> {
                        it.data?.let { response ->
                            response.body()?.let { movies ->
                                movieList = movies
                                moviesAdapter.updateList(movies.results)
                            }
                        }
                    }
                    Status.ERROR -> {
                        Toast.makeText(this.context, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        // TODO:  Loading
                    }
                }
            }
        }
    }

    private fun goToMovie(movie: Movie) {
        val action = MoviesFragmentDirections.actionMoviesFragmentToMovieDetailsFragment(movie)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}