package ir.es.mohammad.netflix

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import ir.es.mohammad.netflix.databinding.FragmentHomeBinding


class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var movies: Array<Movie>
    private lateinit var drawable: Drawable

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)

        val layoutManager = GridLayoutManager(requireContext(), 3)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return 1
            }
        }

        binding.recyclerViewHomeMovies.layoutManager = layoutManager
        val movieAdapter = MovieRecyclerAdapter(movies)

        movieAdapter.setEachItem = { movie ->
            movieImg.setImageDrawable(movie.pic)
            movieTitle.text = movie.name
            if (movie.isFavorite) {
                actionImgBtn.setImageResource(R.drawable.ic_baseline_favorite_24)
                actionImgBtn.isEnabled = false
            } else {
                actionImgBtn.setImageResource(R.drawable.ic_outline_favorite)
                actionImgBtn.setOnClickListener {
                    if (User.registered) {
                        movie.isFavorite = true
                        actionImgBtn.setImageResource(R.drawable.ic_baseline_favorite_24)
                        FavoriteFragment.favoriteMovies.add(movie)
                        actionImgBtn.isEnabled = false
                    }
                    else{
                        val snackbar = Snackbar.make(binding.root, "First You must register", Snackbar.LENGTH_LONG)
                        snackbar.setAction("Register"){
                            val bottomNavigationView = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)
                            bottomNavigationView.selectedItemId = R.id.profileFragment
                        }
                        snackbar.show()
                    }
                }
            }
        }


        binding.recyclerViewHomeMovies.adapter = movieAdapter
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        drawable =
            ResourcesCompat.getDrawable(resources, R.drawable.channels4_profile, context.theme)!!
        movies = Array(21) { Movie("Movie ${it + 1}", drawable) }
    }
}