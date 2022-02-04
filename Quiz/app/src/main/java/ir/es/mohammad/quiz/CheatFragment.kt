package ir.es.mohammad.quiz

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import ir.es.mohammad.quiz.databinding.ActivityCheatBinding
import ir.es.mohammad.quiz.databinding.FragmentCheatBinding
import ir.es.mohammad.quiz.databinding.FragmentQuestionBinding

class CheatFragment : Fragment(R.layout.fragment_cheat) {

    private val viewModel: QuestionModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentCheatBinding.bind(view)

        val answer = viewModel.thisAnswer
        with(binding) {
            btnShowAnswer.setOnClickListener {
                textViewAnswer.text = answer.toString()
                textViewAnswer.visibility = View.VISIBLE
                viewModel.setUserCheated()
            }
        }
    }
}