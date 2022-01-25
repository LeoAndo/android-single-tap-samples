package com.example.singletapsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int index;
    private String[] countries = new String[]{
            "Jamaica",
            "Japan",
            "Jersey",
            "Jordan",
            "Kazakhstan",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView txtResult = findViewById(R.id.txtResult);
        txtResult.setText(countries[index]);

        final SingleTapButton singleTapButton = findViewById(R.id.buttonCountUp);
        singleTapButton.setClickDisablePeriod(1000L); //クリック無効期間を変えたい場合有効化する
        singleTapButton.setOnClickListener(v -> {
            index++;
            if (countries.length <= index) return;
            txtResult.setText(countries[index]);
        });

        final Button button = findViewById(R.id.buttonCountUp2);
        button.setOnClickListener(v -> {
            index++;
            if (countries.length <= index) return;
            txtResult.setText(countries[index]);
        });
    }
}


