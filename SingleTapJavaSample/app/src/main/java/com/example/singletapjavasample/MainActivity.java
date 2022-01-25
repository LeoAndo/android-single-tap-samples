package com.example.singletapjavasample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.singletapjavasample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.setActivity(this);

        binding.button1.setOnClickListener(view -> {
            Log.d(TAG, binding.button1.getText().toString());
            startActivity2();
        });
        binding.button2.setOnClickListener(view -> {
            Log.d(TAG, binding.button2.getText().toString());
            startActivity2();
        });
        binding.button5.setOnClickListener(v -> {
            Log.d(TAG, binding.button5.getText().toString());
            startActivity2();
        });
    }

    public void startActivity2() {
        // ここにブレークポイント貼ってデバックで連打試験する.
        Log.d(TAG, "startActivity2");
        final Intent intent = new Intent(this, SubActivity.class);
        startActivity(intent);
    }
}