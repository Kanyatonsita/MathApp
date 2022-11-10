package com.example.mathapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class AnswerActivity : AppCompatActivity() {

    lateinit var resultView: TextView
    lateinit var rightAnswerView : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        resultView= findViewById(R.id.AnswerView)
        rightAnswerView = findViewById(R.id.rightAnswer)



        val answeredCorrect = intent.getBooleanExtra("answeredCorrect",false)
        Log.d("HHH", "Rätt $answeredCorrect")
        val question = intent.getStringExtra("questionInQuestion")
        val rightAnswer = intent.getStringExtra("correction")

        if (answeredCorrect){
            resultView.text = "Rääätt"
        } else{
            resultView.text = "Tyvärr fel"
            rightAnswerView.text = "Rätt svar är: \n $question = $rightAnswer"
        }


        // launcher Activity betyder att den är den första - sä det ska inte den vaera



    }
}