package com.example.singletapjavasample;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.button.MaterialButton;

public class CustomButton2 extends MaterialButton {
    private static final String TAG = "CustomButton2";
    private long lastClickTime = 0;
    private long clickDisablePeriod = 2000; // クリック無効期間 (ミリ秒)

    public CustomButton2(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean performClick() {
        // 一定期間、クリックを無効化する.
        if (SystemClock.elapsedRealtime() - lastClickTime < clickDisablePeriod) {
            Log.d(TAG, "Disable click for a certain period.");
            // OnClickListenerを呼び出さない.
            return false;
        }
        lastClickTime = SystemClock.elapsedRealtime();

        // 通常のクリックイベント処理を行う.OnClickListenerを呼び出す.
        Log.d(TAG, "Enable click.");
        return super.performClick();
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
