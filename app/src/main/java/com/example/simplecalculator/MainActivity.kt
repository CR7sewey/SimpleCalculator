package com.example.simplecalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.joinAll
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var containerButtons = findViewById<View>(R.id.containerButtons)
        var firstRow = findViewById<LinearLayout>(R.id.firstRow)
        // Buttons - to rework
        var C = findViewById<Button>(R.id.C)
        var plusMinus = findViewById<Button>(R.id.plusMinus)
        var percentage = findViewById<Button>(R.id.percentage)
        var division = findViewById<Button>(R.id.division)
        var seven = findViewById<Button>(R.id.seven)
        var eight = findViewById<Button>(R.id.eight)
        var nine = findViewById<Button>(R.id.nine)
        var multi = findViewById<Button>(R.id.multi)
        var four = findViewById<Button>(R.id.four)
        var five = findViewById<Button>(R.id.five)
        var six = findViewById<Button>(R.id.six)
        var minus = findViewById<Button>(R.id.minus)
        var one = findViewById<Button>(R.id.one)
        var two = findViewById<Button>(R.id.two)
        var third = findViewById<Button>(R.id.third)
        var plus = findViewById<Button>(R.id.plus)
        var dot = findViewById<Button>(R.id.dot)
        var zero = findViewById<Button>(R.id.zero)
        var delete = findViewById<Button>(R.id.delete)
        var equal = findViewById<Button>(R.id.equal)
        var result = findViewById<TextView>(R.id.result)
        var account = findViewById<TextView>(R.id.account)

        var value1: String = "0"
        var value2: String = "0"
        var operation = ""

        nine.setOnClickListener {
            setResult(result, nine)
        }

        eight.setOnClickListener {
            setResult(result, eight)
        }

        seven.setOnClickListener {
            setResult(result, seven)
        }

        six.setOnClickListener {
            setResult(result, six)
        }

        five.setOnClickListener {
            setResult(result, five)}

        four.setOnClickListener {
            setResult(result, four)
        }

        third.setOnClickListener {
            setResult(result, third)
        }

        two.setOnClickListener {
            setResult(result, two)}

        one.setOnClickListener {
            setResult(result, one)
        }
        zero.setOnClickListener {
            setResult(result, zero)
        }

        C.setOnClickListener {
            result.text = "0"
        }
        dot.setOnClickListener {
            var resultDouble1: String = ""
            var resultSplited = result.text.split("")

            println(resultSplited.slice(IntRange(value1.length+2,resultSplited.size-1)).joinToString(""))
            resultDouble1 = resultSplited.slice(IntRange(value1.length+2,resultSplited.size-1)).joinToString("")
            if (resultDouble1 == "" && value1 != "0") {

            }
            else if (!resultDouble1.contains('.') && value1 != "0") {
                result.text = "${result.text}."
            }
            else if (!result.text.contains('.') && value1 == "0") {
                result.text = "${result.text}."
            }


        }

        division.setOnClickListener {
            value1 = result.text.toString()
            operation = setResultSimbols(result,division,operation)
        }
        multi.setOnClickListener {
            value1 = result.text.toString()
            operation = setResultSimbols(result,multi, operation)
        }
        minus.setOnClickListener {
            value1 = result.text.toString()
            operation = setResultSimbols(result,minus, operation)
        }
        plus.setOnClickListener {
            value1 = result.text.toString()
            operation = setResultSimbols(result,plus, operation)
        }

        equal.setOnClickListener {
            //value1 = result.text.toString()
            var notNumbers: Array<String> = arrayOf("+","-","/","x",".")
            if (result.text.last().toString() in notNumbers) {
                Snackbar.make(plus,"Remove the last item.",Snackbar.LENGTH_LONG).show()
            }
            else {
                account.text = result.text.toString()
                getResult(result, operation)
            }
        }

        delete.setOnClickListener {
            if (result.text.length > 1) {
                result.text = if (result.text.subSequence(0, result.text.length - 1)=="") "0" else result.text.subSequence(0, result.text.length - 1)
            }
            else if (result.text.length == 1) {
                result.text = "0"
            }
        }




        /*firstRow.setOnClickListener { selected ->
            println(selected.id)
        }*/


    }

    private fun setResult(result: TextView, element: Button): TextView {
        if (result.text == "0") {
            result.text = element.text
        }
        else {
            result.text = "${result.text}${element.text}"
        }
        return result
    }

    private fun setResultSimbols(result: TextView, element: Button, operation: String): String {
        if (result.text.contains("+") || result.text.contains("-") || result.text.contains("x") || result.text.contains("/")) {
            return result.text.toString()
        }
        else if (result.text[result.text.length -1] == '.') {
            return result.text.toString()
        }

        else {
            result.text = "${result.text}${element.text}"
            val s = element.text.toString()
            return s
        }

    }



    private fun getResult(result: TextView, operation: String): TextView {
        var resultDouble1: Double= 0.0
        var resultDouble2: Double= 0.0
        var resultSplited = result.text.split("")
        var indexOf = resultSplited.indexOf(operation)
        println(resultSplited.slice(IntRange(0,indexOf-1)).joinToString(""))
        resultDouble1 = resultSplited.slice(IntRange(0,indexOf-1)).joinToString("").toDouble()
        println(resultDouble1)
        resultDouble2 = resultSplited.slice(IntRange(indexOf+1, resultSplited.size-1)).joinToString("").toDouble()
        println(resultDouble2)

        if (operation == "+") {
                result.text = (resultDouble1+resultDouble2).toString()
            }
            if (operation == "-") {
                result.text = (resultDouble1-resultDouble2).toString()

            }
            if (operation == "/") {
                result.text = (resultDouble1/resultDouble2).toString()

            }
            if (operation == "x") {
                result.text = (resultDouble1*resultDouble2).toString()
            }
        return result

    }
}