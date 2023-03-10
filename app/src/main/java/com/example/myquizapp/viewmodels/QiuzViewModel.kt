package com.example.myquizapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myquizapp.model.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QiuzViewModel @Inject constructor(private val repo: Repo) : ViewModel() {
    val currentQuestionId = MutableLiveData<Int>(0)
    val currentQuestion = MutableLiveData<String>()
    var userAnswer = -1
    var userAnswers = Array<Int>(getQuestionsAmount()) { 0 }

    fun getQuestionsAmount() = repo.data.size

    fun loadNextQuestion() {
        currentQuestionId.value = currentQuestionId.value!! + 1
        currentQuestion.value = repo.data[currentQuestionId.value!!]
    }

    fun loadPreviousQuestion() {
        currentQuestionId.value = currentQuestionId.value!! - 1
        currentQuestion.value = repo.data[currentQuestionId.value!!]

    }

    fun loadCurrentQuestion() {
        currentQuestion.value = repo.data[currentQuestionId.value!!]
    }

    fun saveUserAnswer() {
        userAnswers[currentQuestionId.value!!] = userAnswer

    }

    fun loadAnswers(questionNumber: Int): ArrayList<String> {

        return repo.answersAsVariants[questionNumber]
    }

    fun getRightAmount(): Int {

        var correctAnswers = 0

        for (i in userAnswers.indices) {
            if (userAnswers[i] == repo.answersId[i]) {
                correctAnswers++
            }
        }

        return correctAnswers
    }


    fun getComment(): CharSequence? {

        val res = getRightAmount()

        return if (res == getQuestionsAmount()) {
            "You are real football fan!"

        } else {
            "Maybe, you should try again"
        }

    }

    fun restartQuiz() {
        currentQuestionId.postValue(0)
        userAnswer = -1
        for (i in userAnswers.indices) {
            userAnswers[i] = -1
        }

    }

    fun getresultMessage(): String {

        return "Hey, take challenge in quiz, where i got ${getRightAmount()}/${getQuestionsAmount()}! It was pretty cool!"

    }

}
