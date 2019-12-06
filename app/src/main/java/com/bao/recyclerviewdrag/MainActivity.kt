package com.bao.recyclerviewdrag

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_all.setOnClickListener {
            startActivity(Intent(this@MainActivity, TestActivity2::class.java))
        }

        btn_image.setOnClickListener {
            startActivity(Intent(this@MainActivity, TestActivity1::class.java))
        }
    }
}
