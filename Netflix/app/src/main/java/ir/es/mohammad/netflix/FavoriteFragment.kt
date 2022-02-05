package ir.es.mohammad.netflix

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import ir.es.mohammad.netflix.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    companion object {
        val favoriteMovies = mutableSetOf<Movie>()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentFavoriteBinding.bind(view)

        val layoutManager = GridLayoutManager(requireContext(), 3)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return 1
            }
        }

        binding.recyclerViewFavoriteMovies.layoutManager = layoutManager
        val movieAdapter = MovieRecyclerAdapter(favoriteMovies.toTypedArray())

        binding.recyclerViewFavoriteMovies.adapter = movieAdapter

    }
}