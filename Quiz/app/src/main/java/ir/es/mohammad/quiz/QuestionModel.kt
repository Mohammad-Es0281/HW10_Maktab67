package ir.es.mohammad.quiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuestionModel : ViewModel() {

    private val questions =
        Array(10) { "Is this a homework from ${if (it % 2 == 0) "maktab" else "university"}?" }
    private val answers = Array(10) { it % 2 == 0 }
    private var userAnswers = Array(10) { AnswerStatus.NotAnswered }
    private var questionNumber = 0

    val thisQuestion: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val thisUserAnswer: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val thisAnswer
        get() = answers[questionNumber]

    private enum class AnswerStatus() {
        NotAnswered, Correct, Incorrect, Cheat
    }

    init {
        thisQuestion.value = questions[0]
    }

    fun isAnswered(): Boolean = userAnswers[questionNumber] != AnswerStatus.NotAnswered

    fun isCheated(): Boolean = userAnswers[questionNumber] == AnswerStatus.Cheat

    fun isFirstQuestion(): Boolean = questionNumber == 0

    fun isLastQuestion(): Boolean = questionNumber == questions.lastIndex

    fun gotoNextQuestion(): String {
        if (questionNumber + 1 == questions.size)
            throw Exception()
        val qu = questions[++questionNumber]
        thisQuestion.value = qu
        thisUserAnswer.value = userAnswers[questionNumber].toString()
        return qu
    }

    fun gotoPrevQuestion(): String {
        if (questionNumber - 1 == -1)
            throw Exception()
        val qu = questions[--questionNumber]
        thisQuestion.value = qu
        thisUserAnswer.value = userAnswers[questionNumber].toString()
        return qu
    }

    fun getThisQuestionAnswer(): Boolean = answers[questionNumber]

    fun setUserAnswer(answer: Boolean): Boolean {
        val isAnswerCorrect = answer == answers[questionNumber]
        return if (isAnswerCorrect) {
            userAnswers[questionNumber] = AnswerStatus.Correct
            thisUserAnswer.value = AnswerStatus.Correct.toString()
            true
        } else {
            userAnswers[questionNumber] = AnswerStatus.Incorrect
            thisUserAnswer.value = AnswerStatus.Incorrect.toString()
            false
        }
    }

    fun setUserCheated() {
        userAnswers[questionNumber] = AnswerStatus.Cheat
        thisUserAnswer.value = AnswerStatus.Cheat.toString()
    }
}