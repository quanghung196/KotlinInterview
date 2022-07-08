package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    var a: Int = 9
    var b: MutableList<Int> = mutableListOf(0,1,2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        print(b);
    }

    fun increaseByOne(x: MutableList<Int>) {
        x.add(4)
    }
}