package com.example.mathapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {


    lateinit var questionTextView: TextView
    lateinit var answerView: EditText
    lateinit var roundWinCounter: TextView
    lateinit var roundRightAnswers: TextView

    var firstNumber = 0
    var secondNumber = 0
    var thirdNumber = 0
    var numberForMathfunktion = 0
    var correctAnswer = 0


    var question = ""

    var rounds = 0
    var rightAnswers = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        roundWinCounter = findViewById(R.id.roundNWinstcounter)
        roundRightAnswers = findViewById(R.id.roundRightAnswers)

        questionTextView = findViewById(R.id.textView)
        answerView = findViewById(R.id.answerEditText)
        val button = findViewById<Button>(R.id.button2)
        setNewQuestion()

        button.setOnClickListener {

            checkAnswerAndStartAnswerActivity()

        }


    }

    override fun onResume() {
        super.onResume()
        rounds++
        roundWinCounter.text = "Round $rounds"
        roundRightAnswers.text = "Correct answers: $rightAnswers"

        answerView.setText("")
        setNewQuestion()

    }


    fun checkAnswerAndStartAnswerActivity() {
        val answerText = answerView.text.toString()
        val answer = answerText.toIntOrNull()

        when (numberForMathfunktion) {
            1 -> correctAnswer = firstNumber + secondNumber
            2 -> correctAnswer = firstNumber - secondNumber
            3 -> correctAnswer = firstNumber * secondNumber
            4 -> correctAnswer = thirdNumber / secondNumber
        }


        var answeredCorrect = false


        if (answer == correctAnswer) {
            answeredCorrect = true
            rightAnswers++
        }

        val intent = Intent(this, AnswerActivity::class.java)
        intent.putExtra("answeredCorrect", answeredCorrect)
        intent.putExtra("questionInQuestion", question)
        intent.putExtra("correction",correctAnswer.toString())


        startActivity(intent)

        // det funka inte att göra en editable direkt till en Int
        // utan man måste göra det först till en sträng o sen till int
        // andra aktiviteten sak ska visa upp vad rätt svar var och flrer räkne sätt
        // kanske mer funktioner (kanske slumpa fram -
        //sks sen börjar om


    }


    fun setNewQuestion(){
        firstNumber = (1..10).random()
        secondNumber = (1..10).random()
        thirdNumber = firstNumber * secondNumber
        numberForMathfunktion = (1..4).random()


        // if sats random tecken 1=+ 2= - 3= / och 4=*
        question = when (numberForMathfunktion) {

            1 -> "$firstNumber + $secondNumber"
            2 -> "$firstNumber - $secondNumber"
            3 -> "$firstNumber * $secondNumber"
            4 ->  "$thirdNumber / $secondNumber"
            else -> ""
        }

        questionTextView?.text = question

        // if sats om numberForMathfun är 4 - first måste firstnumber > secondnummer -
        // //sen måste det var delbart jämnt annars måste


    }


}