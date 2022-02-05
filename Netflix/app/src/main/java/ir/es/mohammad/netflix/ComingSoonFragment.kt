package ir.es.mohammad.netflix

import android.content.Context
import android.content.Intent
import android.content.Intent.EXTRA_TEXT
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import ir.es.mohammad.netflix.databinding.FragmentComingSoonBinding


class ComingSoonFragment : Fragment(R.layout.fragment_coming_soon) {

    private lateinit var intentActivityResultLauncher: ActivityResultLauncher<Intent>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentComingSoonBinding.bind(view)

        val drawable = ResourcesCompat.getDrawable(
            resources,
            R.drawable.channels4_profile,
            requireContext().theme
        )

        val movies = Array(3) { Movie("Movie ${it + 1 + 21}", drawable!!) }

        binding.recyclerViewComingSoonMovies.layoutManager = LinearLayoutManager(requireContext())

        val movieAdapter = MovieRecyclerAdapter(movies)

        movieAdapter.setEachItem = { movie ->
            movieImg.setImageDrawable(movie.pic)
            movieTitle.text = movie.name
            actionImgBtn.setImageResource(R.drawable.ic_baseline_share_24)
            actionImgBtn.setOnClickListener {
                if (User.registered) {
                    intentActivityResultLauncher.launch(createShareText(movie.name))
                } else {
                    val snackbar =
                        Snackbar.make(binding.root, "First You must register", Snackbar.LENGTH_LONG)
                    snackbar.setAction("Register") {
                        val bottomNavigationView = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)
                        bottomNavigationView.selectedItemId = R.id.profileFragment
                    }
                    snackbar.show()
                }
            }

        }
        binding.recyclerViewComingSoonMovies.adapter = movieAdapter
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        intentActivityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {}
    }

    private fun createShareText(text: String): Intent {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(EXTRA_TEXT, text)
        }
        return intent
    }
}