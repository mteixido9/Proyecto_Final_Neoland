package com.example.bravadive.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bravadive.R
import com.example.bravadive.Spot
import kotlinx.android.synthetic.main.activity_detail.*


class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iVLogo.setImageResource(R.mipmap.brava_dive_oval)


    }
}