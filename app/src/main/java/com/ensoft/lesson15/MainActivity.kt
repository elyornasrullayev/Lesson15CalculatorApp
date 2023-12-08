package com.ensoft.lesson15

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.text.StringBuilder

class MainActivity : AppCompatActivity() {

    private var value1: Double = 0.0
    private var value2: Double = 0.0
    private var operation: String = ""

    private var finalResult: Double = 0.0

    private var stringBuilder: StringBuilder = StringBuilder()

    lateinit var btn_zero:Button
    lateinit var btn_one:Button
    lateinit var btn_two:Button
    lateinit var btn_three:Button
    lateinit var btn_four:Button
    lateinit var btn_five:Button
    lateinit var btn_six:Button
    lateinit var btn_seven:Button
    lateinit var btn_eight:Button
    lateinit var btn_nine:Button
    lateinit var btn_dot:Button
    lateinit var btn_c:Button
    lateinit var btn_del:Button
    lateinit var btn_div:Button
    lateinit var btn_mult:Button
    lateinit var btn_add:Button
    lateinit var btn_substract:Button
    lateinit var btn_equal:Button
    lateinit var et_result:EditText
    lateinit var tv_result:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_zero = findViewById(R.id.btn_zero)
        btn_one = findViewById(R.id.btn_one)
        btn_two = findViewById(R.id.btn_two)
        btn_three = findViewById(R.id.btn_three)
        btn_four = findViewById(R.id.btn_four)
        btn_five = findViewById(R.id.btn_five)
        btn_six = findViewById(R.id.btn_six)
        btn_seven = findViewById(R.id.btn_seven)
        btn_eight = findViewById(R.id.btn_eight)
        btn_nine = findViewById(R.id.btn_nine)
        btn_dot = findViewById(R.id.btn_dot)
        btn_c = findViewById(R.id.btn_c)
        btn_del = findViewById(R.id.btn_del)
        btn_div = findViewById(R.id.btn_div)
        btn_mult = findViewById(R.id.btn_mult)
        btn_add = findViewById(R.id.btn_add)
        btn_substract = findViewById(R.id.btn_substract)
        btn_equal = findViewById(R.id.btn_equal)
        et_result = findViewById(R.id.et_result)
        tv_result = findViewById(R.id.tv_result)

        //Numbers and symbols
        btn_zero.setOnClickListener { btnClick(btn_zero) }
        btn_one.setOnClickListener { btnClick(btn_one) }
        btn_two.setOnClickListener { btnClick(btn_two) }
        btn_three.setOnClickListener { btnClick(btn_three) }
        btn_four.setOnClickListener { btnClick(btn_four) }
        btn_five.setOnClickListener { btnClick(btn_five) }
        btn_six.setOnClickListener { btnClick(btn_six) }
        btn_seven.setOnClickListener { btnClick(btn_seven) }
        btn_eight.setOnClickListener { btnClick(btn_eight) }
        btn_nine.setOnClickListener { btnClick(btn_nine) }
        btn_dot.setOnClickListener { btnClick(btn_dot) }
        btn_c.setOnClickListener { btnClick(btn_c)}
        btn_del.setOnClickListener { btnClick(btn_del) }

        //Math operation buttons
        btn_div.setOnClickListener { btnMath(btn_div) }
        btn_mult.setOnClickListener { btnMath(btn_mult) }
        btn_add.setOnClickListener { btnMath(btn_add) }
        btn_substract.setOnClickListener { btnMath(btn_substract) }

        btn_equal.setOnClickListener { btnEqual(btn_equal) }

    }

    private fun btnClick(view: View) {
        var value = et_result.text.toString()

        when (view.id) {
            btn_zero.id -> {
                value += "0"
                stringBuilder.append("0")
            }
            btn_one.id -> {
                value += "1"
                stringBuilder.append("1")
            }
            btn_two.id -> {
                value += "2"
                stringBuilder.append("2")
            }
            btn_three.id -> {
                value += "3"
                stringBuilder.append("3")
            }
            btn_four.id -> {
                value += "4"
                stringBuilder.append("4")
            }
            btn_five.id -> {
                value += "5"
                stringBuilder.append("5")
            }
            btn_six.id -> {
                value += "6"
                stringBuilder.append("6")
            }
            btn_seven.id -> {
                value += "7"
                stringBuilder.append("7")
            }
            btn_eight.id -> {
                value += "8"
                stringBuilder.append("8")
            }
            btn_nine.id -> {
                value += "9"
                stringBuilder.append("9")
            }
            btn_dot.id -> {
                value += "."
                stringBuilder.append(".")
            }
            btn_c.id -> {
                if(stringBuilder.contains("=")){
                    et_result.text.clear()
                }
                value = ""
                stringBuilder.clear()
            }
            btn_del.id -> {
                value = et_result.text.toString()
                if (value.isNotEmpty()) {
                    value = value.substring(0, value.length - 1)
                }

                if(stringBuilder.isNotEmpty()){
                    stringBuilder.deleteCharAt(stringBuilder.length - 1)

                    if(stringBuilder.contains("=")){
                        value = ""
                        stringBuilder.clear()
                    }
                }
            }
        }
        et_result.setText(value)
        tv_result.text = stringBuilder.toString()
    }
    private fun btnMath(view: View){
        when(view.id){
            btn_div.id -> operation = "/"
            btn_mult.id -> operation = "*"
            btn_add.id -> operation = "+"
            btn_substract.id -> operation = "-"
        }

        if(et_result.text.isNotEmpty()){
            val value = et_result.text.toString()
            value1 = value.toDouble()
            stringBuilder.append(" $operation ")
            tv_result.text = stringBuilder.toString()
            //Clear edittext after getting the first value
            //Birinchi qiymat olingandan keyin edittext o'chirib tashlash
            et_result.setText("")
        }
    }
    private fun btnEqual(view: View){
        if(et_result.text.isNotEmpty()){
            val value = et_result.text.toString()
            value2 = value.toDouble()
        }

        when(operation){
            "+" -> finalResult = value1 + value2
            "-" -> finalResult = value1 - value2
            "*" -> finalResult = value1 * value2
            "/" -> try {
                finalResult = value1 / value2
            }catch (ae: ArithmeticException){
                et_result.setText("")
                tv_result.text = ""
            }
        }

        /*The word Infinity comes when try to divide by 0*/
        if(finalResult.toString() == "Infinity"){
            stringBuilder.clear()
            et_result.setText("")
            tv_result.text = ""
        }else{
            stringBuilder.append(" = ")
            stringBuilder.append(finalResult)

            et_result.setText(finalResult.toString())
            tv_result.text = stringBuilder.toString()
        }

    }
}