package com.example.myquizapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myquizapp.model.Repo

class QiuzViewModel(private val repo: Repo ) : ViewModel() {
    val currentQuestionId = MutableLiveData<Int>(0)
    val currentQuestion = MutableLiveData<String>()
    var userAnswer = -1
    var userAnswers = Array<Int>(getQuestionsAmount()) {0}

    fun getQuestionsAmount() = repo.data.size

    fun loadNextQuestion(){
        currentQuestionId.value = currentQuestionId.value!! + 1
        currentQuestion.value = repo.data[currentQuestionId.value!!]
    }

    fun loadPreviousQuestion() {
        currentQuestionId.value = currentQuestionId.value!! -1
        currentQuestion.value = repo.data[currentQuestionId.value!!]

    }

    fun loadCurrentQuestion() {
        currentQuestion.value = repo.data[currentQuestionId.value!!]
    }

    fun saveUserAnswer() {
        userAnswers[currentQuestionId.value!!] = userAnswer

    }

    fun loadAnswers(questionNumber: Int) : ArrayList<String>{

        return repo.answersAsVariants[questionNumber]
    }


}
