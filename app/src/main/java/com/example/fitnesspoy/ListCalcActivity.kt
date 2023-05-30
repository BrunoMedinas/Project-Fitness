package com.example.fitnesspoy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ListCalcActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_calc)

        //buscar no banco esse tipo      elvis == else       throw vai lançar esse erro, caso no valor nao
        val type = intent?.extras?.getString("type") ?: throw IllegalAccessException("Type not found!")
    }
}