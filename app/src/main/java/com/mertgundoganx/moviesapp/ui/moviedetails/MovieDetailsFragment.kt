package com.mertgundoganx.moviesapp.ui.moviedetails

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.mertgundoganx.moviesapp.databinding.MovieDetailsFragmentBinding
import com.mertgundoganx.moviesapp.data.model.MoviesResults

class MovieDetailsFragment : Fragment() {

    private val viewModel: MovieDetailsViewModel by viewModels()
    private var _binding: MovieDetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MovieDetailsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieModel: MoviesResults = args.movie
        loadMovieData(movieModel)

    }

    @SuppressLint("SetTextI18n")
    private fun loadMovieData(movieModel: MoviesResults) {
        binding.apply {
            movieTitle.text = movieModel.title
            movieImage.load("https://image.tmdb.org/t/p/original/${movieModel.poster_path}")
            movieDescription.text = movieModel.overview
            movieDate.text = "Release Date: ${movieModel.release_date}"
            movieVote.text = movieModel.vote_average.toString()
            movieCount.text = movieModel.vote_count.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}