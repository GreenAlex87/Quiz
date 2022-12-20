package com.example.myquizapp.ui

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myquizapp.R
import com.example.myquizapp.databinding.FragmentQuestionBinding
import com.example.myquizapp.viewmodels.QiuzViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuestionFragment : Fragment() {

    private var _binding: FragmentQuestionBinding? = null
    private val binding get() = _binding!!

    private val viewModel: QiuzViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentQuestionBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupViews()
    }

    private fun setupViews() {

        binding.prevButton.setOnClickListener {
            viewModel.saveUserAnswer()
            viewModel.loadPreviousQuestion()

        }
        binding.allTextView.text = viewModel.getQuestionsAmount().toString()


        binding.radios.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.firstRB -> {
                    viewModel.userAnswer = 1
                }
                R.id.secondRB -> {
                    viewModel.userAnswer = 2
                }
                R.id.thirdRB -> {
                    viewModel.userAnswer = 3
                }
                R.id.fouthRB -> {
                    viewModel.userAnswer = 4
                }
                R.id.fifthRB -> {
                    viewModel.userAnswer = 5
                }
            }
        }

    }


    private fun setupObservers() {
        viewModel.currentQuestionId.observe(viewLifecycleOwner) { questionNumber ->
            viewModel.loadCurrentQuestion()
            binding.prevButton.isEnabled = questionNumber != 0
            if (questionNumber == viewModel.getQuestionsAmount() - 1) {
                binding.nextButton.text = "Finish"
                binding.nextButton.setOnClickListener {
                    viewModel.saveUserAnswer()
                    findNavController().navigate(R.id.action_questionFragment_to_quizResultFragment)
                }

            } else {
                binding.nextButton.text = ">" //$gt
                binding.nextButton.setOnClickListener {
                    viewModel.saveUserAnswer()
                    viewModel.loadNextQuestion()
                }
            }

            binding.currentTextView.text = (questionNumber + 1).toString()
            setUpAnswers(questionNumber)

        }

        viewModel.currentQuestion.observe(viewLifecycleOwner) { question ->
            binding.questionTextView.text = question
            loadUserAnswerIfExistAtQuestion(viewModel.currentQuestionId.value!!)
        }
    }

    private fun loadUserAnswerIfExistAtQuestion(questionNumber: Int) {

        when (viewModel.userAnswers[questionNumber]) {
            1 -> {
                binding.radios.check(R.id.firstRB)
                viewModel.userAnswer = 1
            }
            2 -> {
                binding.radios.check(R.id.secondRB)
                viewModel.userAnswer = 2
            }
            3 -> {
                binding.radios.check(R.id.thirdRB)
                viewModel.userAnswer = 3
            }
            4 -> {
                binding.radios.check(R.id.fouthRB)
                viewModel.userAnswer = 4
            }
            5 -> {
                binding.radios.check(R.id.fifthRB)
                viewModel.userAnswer = 5
            }
            else -> {
                binding.radios.clearCheck()
                viewModel.userAnswer = -1
            }
        }
    }


    private fun setUpAnswers(questionNumber: Int) {

        val answers = viewModel.loadAnswers(questionNumber)

        binding.firstRB.text = answers[0]
        binding.secondRB.text = answers[1]
        binding.thirdRB.text = answers[2]
        binding.fouthRB.text = answers[3]
        binding.fifthRB.text = answers[4]
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}