package com.godspeed.tip_calculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.godspeed.tip_calculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil
import kotlin.math.round

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            calctip()
        }
    }

    @SuppressLint("StringFormatInvalid")
    fun calctip(){
        val service=binding.textview3.text.toString()
        val cost=service.toDoubleOrNull()
        if(cost==null){
            binding.textView2.text = ""
            return
        }
        val tipPercentage = when (binding.radioGroup.checkedRadioButtonId) {
            R.id.radioButton -> 0.20
            R.id.radioButton2 -> 0.10
            else -> 0.05
        }
        var tip=tipPercentage*cost
        val roundup=binding.switch1.isChecked
        if (roundup) {
            tip = ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.textView2.text=getString(R.string.tip_amount, formattedTip)
    }
}