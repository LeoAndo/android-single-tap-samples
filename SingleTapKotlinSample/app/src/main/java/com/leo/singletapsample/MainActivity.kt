package com.leo.singletapsample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.leo.singletapsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        val TAG = MainActivity::class.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.activity = this

        binding.button1.setOnClickListener {
            Log.d(TAG, binding.button1.text.toString())
            startActivity2()
        }
        binding.button2.setOnClickListener {
            Log.d(TAG, binding.button2.text.toString())
            startActivity2()
        }
        binding.button5.setOnClickListener {
            Log.d(TAG, binding.button5.text.toString())
            startActivity2()
        }
        binding.button7.setOnSingleClickListener {
            Log.d(TAG, binding.button7.text.toString())
            startActivity2()
        }
        binding.button8.setOnDebounceClickListener {
            Log.d(TAG, binding.button8.text.toString())
            startActivity2()
        }
        binding.button9.setOnSingleTouchListener {
            Log.d(TAG, binding.button9.text.toString())
        }
    }

    fun startActivity2() {
        // TODO ここにブレークポイント貼ってデバックで連打試験する.
        Log.d(TAG, "startActivity2")
        val intent = Intent(this, SubActivity::class.java)
        startActivity(intent)
    }
}
