package com.leo.singletapsample

import android.os.SystemClock
import android.util.Log
import android.view.View

// https://github.com/ApturiCOVID/apturicovid-android/blob/fa8ba810a5bc95eaadac344f10cba36df1c9fac4/app/src/main/java/lv/spkc/apturicovid/utils/DebounceClickListener.kt
abstract class DebouncedClickListener constructor(private var defaultInterval: Long = 2000L) :
    View.OnClickListener {
    private var lastTimeClicked: Long = 0

    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval) {
            Log.d("DebouncedClickListener", "Disable click for a certain period.")
            return
        }
        lastTimeClicked = SystemClock.elapsedRealtime()
        performClick(v)
    }

    abstract fun performClick(v: View)
}