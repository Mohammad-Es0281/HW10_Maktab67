package ir.es.mohammad.quiz

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import ir.es.mohammad.quiz.databinding.FragmentQuestionBinding
import java.util.*

class QuestionFragment : Fragment(R.layout.fragment_question) {

    private lateinit var binding: FragmentQuestionBinding

    private fun showToast(message: String) =
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()

    private val viewModel: QuestionModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onCreate(savedInstanceState)
        binding = FragmentQuestionBinding.bind(view)

        with(binding) {
            viewModel.thisQuestion.observe(this@QuestionFragment){ textViewQuestion.text = it }

            viewModel.thisUserAnswer.observe(this@QuestionFragment){
                if (it != "NotAnswered") {
                    answerButtonsSetIsEnable(false)
                    if (it == "Cheat")
                        showToast("Cheat ☹️ Answer = ${viewModel.getThisQuestionAnswer()}")
                }
                else
                    answerButtonsSetIsEnable(true)
            }

            btnTrue.setOnClickListener { onAnswerBtnClick(true) }

            btnFalse.setOnClickListener { onAnswerBtnClick(false) }

            btnCheat.setOnClickListener {
                view.findNavController().navigate(
                    QuestionFragmentDirections.actionQuestionFragmentToCheatFragment(viewModel.getThisQuestionAnswer())
                )
            }

            btnNext.setOnClickListener { onChangeQuestionBtnClick(true) }

            btnPrev.setOnClickListener { onChangeQuestionBtnClick(false) }
        }
    }

    private fun onChangeQuestionBtnClick(isBtnNext: Boolean){
        if (isBtnNext)
            viewModel.gotoNextQuestion()
        else
            viewModel.gotoPrevQuestion()

        binding.btnNext.isEnabled = !viewModel.isLastQuestion()
        binding.btnPrev.isEnabled = !viewModel.isFirstQuestion()
    }

    private fun onAnswerBtnClick(btnBoolean: Boolean) {
        val wasCorrect = viewModel.setUserAnswer(btnBoolean)
        if (wasCorrect)
            showToast("Correct!")
        else
            showToast("Incorrect!")

    }

    private fun answerButtonsSetIsEnable(isEnabled: Boolean) {
        with(binding) {
            btnTrue.isEnabled = isEnabled
            btnFalse.isEnabled = isEnabled
            btnCheat.isEnabled = isEnabled
        }
    }
}