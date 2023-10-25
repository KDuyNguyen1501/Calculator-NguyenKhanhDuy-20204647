package com.example.caculatorconstrains

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun clearEntryAction(view: View) {
        var currentNumber = findViewById<TextView>(R.id.currentNumber)
        currentNumber.text = "0"
    }

    fun clearAction(view: View) {
        var currentNumber = findViewById<TextView>(R.id.currentNumber)
        var currentExpresstion = findViewById<TextView>(R.id.currentExpression)
        currentNumber.text = "0"
        currentExpresstion.text = ""
    }

    fun backSpaceAction(view: View) {
        var currentNumber = findViewById<TextView>(R.id.currentNumber)
        var toNumber = Integer.parseInt(currentNumber.text.toString()) / 10
        currentNumber.text = Integer.toString(toNumber)
    }

    fun numberClickAction(view: View) {
        var currentNumber = findViewById<TextView>(R.id.currentNumber)
        if (view is Button) {
            if (currentNumber.text == "0") {
                currentNumber.text = view.text
            } else {
                currentNumber.text = currentNumber.text.toString() + view.text.toString()
            }
        }
    }

    fun operatorClickAction(view: View) {
        if (view is Button) {
            var currentNumber = findViewById<TextView>(R.id.currentNumber)
            var currentExpresstion = findViewById<TextView>(R.id.currentExpression)

            if (currentExpresstion.text == "") {
                currentExpresstion.text = currentNumber.text.toString() + view.text
                currentNumber.text = "0"
            }
            if (!currentExpresstion.text[currentExpresstion.text.length - 1].isDigit()) {
                currentExpresstion.text =
                    currentExpresstion.text.substring(range = 0..currentExpresstion.text.length - 2) + view.text
                currentNumber.text = "0"
            }
        }

    }

    fun equalAction(view: View) {
        var currentNumber = findViewById<TextView>(R.id.currentNumber)
        var currentExpresstion = findViewById<TextView>(R.id.currentExpression)

        var result: Int = 0

        if (currentNumber.text.isDigitsOnly()) {
            result = Integer.parseInt(currentNumber.text.toString())
        }

        if (currentExpresstion.text != "") {
            val operator: Char = currentExpresstion.text.last()
            if (
                !operator.isDigit()
            ) {
                val firstNumber = Integer.parseInt(
                    currentExpresstion.text.substring(range = 0..currentExpresstion.text.length - 2)
                )
                when (operator) {
                    '+' -> {
                        result = firstNumber + Integer.parseInt(currentNumber.text.toString())
                    }

                    '-' -> {
                        result = firstNumber - Integer.parseInt(currentNumber.text.toString())
                    }

                    '*' -> {
                        result = firstNumber * Integer.parseInt(currentNumber.text.toString())
                    }

                    '/' -> {
                        result = firstNumber / Integer.parseInt(currentNumber.text.toString())
                    }

                    else -> {
                        result = 0
                    }

                }
                currentExpresstion.text = currentExpresstion.text.toString() + currentNumber.text
            }

        }
        currentNumber.text = result.toString()
    }
}