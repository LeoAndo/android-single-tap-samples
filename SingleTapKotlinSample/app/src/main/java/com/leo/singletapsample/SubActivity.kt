package com.leo.singletapsample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SubActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this, "SubActivity onCreate", Toast.LENGTH_SHORT).show()
    }
}
