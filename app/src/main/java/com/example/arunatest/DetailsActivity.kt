package com.example.arunatest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        textView.text = intent.extras!!.getString("passselected")!!
        textView2.text = intent.extras!!.getString("bodyData")!!
    }
}