package com.example.singletapjavasample;

import android.os.SystemClock;
import android.util.Log;
import android.view.View;

public abstract class DebouncedClickListener implements View.OnClickListener {
    private long lastTimeClicked = 0;

    @Override
    public void onClick(View v) {
        final long defaultInterval = 2000L;
        if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval) {
            Log.d("DebouncedClickListener", "Disable click for a certain period.");
            return;
        }
        lastTimeClicked = SystemClock.elapsedRealtime();
        performClick(v);
    }

    abstract void performClick(View view);
}