package com.example.singletapjavasample;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.button.MaterialButton;

public class CustomButton extends MaterialButton {
    private static final String TAG = "CustomButton";
    private long lastClickTime = 0;
    private long clickDisablePeriod = 2000; // クリック無効期間 (ミリ秒)

    public CustomButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * [android.view.View.onTouchEvent]
     *
     * @return true: 「TouchEventを消化」したことになり、後続処理のClick Eventが発火させない.
     * false: TouchEvent消化しないで、後続処理(Click Event等)を行う.
     */
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 一定期間、クリックイベント発火させない.
        if (SystemClock.elapsedRealtime() - lastClickTime < clickDisablePeriod) {
            Log.d(TAG, "Disable click for a certain period.");
            return true;
        }
        // Viewのタッチアップ後、一定期間onClickを発火させないでダブルタップを抑止する.
        if (event.getAction() == MotionEvent.ACTION_UP) {
            Log.d(TAG, "action is ACTION_UP");
            lastClickTime = SystemClock.elapsedRealtime();
        }
        // 通常のタッチイベント処理を行う.
        return super.onTouchEvent(event);
    }

    /**
     * クリック無効期間を設定する.
     *
     * @param clickDisablePeriod クリック無効期間 (ミリ秒)
     */
    public void setClickDisablePeriod(long clickDisablePeriod) {
        this.clickDisablePeriod = clickDisablePeriod;
    }
}
