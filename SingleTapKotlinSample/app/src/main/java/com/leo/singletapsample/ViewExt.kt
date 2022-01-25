package com.leo.singletapsample

import android.annotation.SuppressLint
import android.os.SystemClock
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.databinding.BindingAdapter

/**
 * SingleClick
 *
 * 2021-06-08 00:08:34.020 13472-13472/leo.singletapsample D/setOnSingleClickListener: 通常のクリックイベント処理を行う
 * 2021-06-08 00:08:34.021 13472-13472/leo.singletapsample D/MainActivity: ext setOnSingleClickListener activity
 * 2021-06-08 00:08:34.152 13472-13472/leo.singletapsample D/setOnSingleClickListener: Disable click for a certain period.
 * 2021-06-08 00:08:35.863 13472-13472/leo.singletapsample D/setOnSingleClickListener: Disable click for a certain period.
 * 2021-06-08 00:08:36.116 13472-13472/leo.singletapsample D/setOnSingleClickListener: 通常のクリックイベント処理を行う
 *
 * @param listener クリックリスナー
 */
@BindingAdapter("onSingleClick")
fun View.setOnSingleClickListener(listener: View.OnClickListener) {
    val clickDisablePeriod = 2000L // クリック無効期間
    var lastClickTime = 0L
    this.setOnClickListener {
        if (SystemClock.elapsedRealtime() - lastClickTime < clickDisablePeriod) {
            Log.d("setOnSingleClickListener", "Disable click for a certain period.")
            return@setOnClickListener
        }
        Log.d("setOnSingleClickListener", "通常のクリックイベント処理を行う")
        lastClickTime = SystemClock.elapsedRealtime()
        listener.onClick(it)
    }
}

/**
 * SingleTap: onTouchEvent.
 *
 * 2021-06-08 00:04:25.205 13296-13296/leo.singletapsample D/setOnSingleTouchListener: action is ACTION_UP
 * 2021-06-08 00:04:25.205 13296-13296/leo.singletapsample D/MainActivity: ext setOnSingleTouchListener activity
 * 2021-06-08 00:04:25.287 13296-13296/leo.singletapsample D/setOnSingleTouchListener: Disable click for a certain period.
 * 2021-06-08 00:04:26.983 13296-13296/leo.singletapsample D/setOnSingleTouchListener: Disable click for a certain period.
 * 2021-06-08 00:04:27.198 13296-13296/leo.singletapsample D/setOnSingleTouchListener: Disable click for a certain period.
 * 2021-06-08 00:04:27.261 13296-13296/leo.singletapsample D/setOnSingleTouchListener: action is ACTION_UP
 * 2021-06-08 00:04:27.262 13296-13296/leo.singletapsample D/MainActivity: ext setOnSingleTouchListener activity
 *
 * @param touchDisablePeriod タッチ無効期間 (ミリ秒)
 * @param onTouchActionUp タッチアップ時のコールバック
 */
@SuppressLint("ClickableViewAccessibility")
fun View.setOnSingleTouchListener(touchDisablePeriod: Long = 2000L, onTouchActionUp: () -> Unit) {
    var lastTouchTime = 0L
    this.setOnTouchListener { _, event ->
        // 一定期間、クリックイベント発火させない.
        if (SystemClock.elapsedRealtime() - lastTouchTime < touchDisablePeriod) {
            Log.d("setOnSingleTouchListener", "Disable click for a certain period.")
            // trueで、「TouchEventを消化」したことになり、クリックイベント発火させない.
            // true: 消化. false: 消化しないで、後続処理(click event等)を行う.
            return@setOnTouchListener true
        }

        // Viewのタッチアップ後、一定期間onClickを発火させないでダブルタップを抑止する.
        if (event.action == MotionEvent.ACTION_UP) {
            Log.d("setOnSingleTouchListener", "action is ACTION_UP")

            lastTouchTime = SystemClock.elapsedRealtime()
            onTouchActionUp.invoke()
        }

        // 通常のタッチイベント処理を行う.
        return@setOnTouchListener this.onTouchEvent(event)
    }
}

/**
 * SingleClick
 *
 * 2021-06-08 00:06:06.443 13296-13296/leo.singletapsample D/setOnDebounceClickListener: 通常のクリックイベント処理を行う
 * 2021-06-08 00:06:06.443 13296-13296/leo.singletapsample D/MainActivity: ext DebouncedClickListener activity
 * 2021-06-08 00:06:06.592 13296-13296/leo.singletapsample D/DebouncedClickListener: Disable click for a certain period.
 * 2021-06-08 00:06:08.262 13296-13296/leo.singletapsample D/DebouncedClickListener: Disable click for a certain period.
 * 2021-06-08 00:06:08.401 13296-13296/leo.singletapsample D/DebouncedClickListener: Disable click for a certain period.
 * 2021-06-08 00:06:08.538 13296-13296/leo.singletapsample D/setOnDebounceClickListener: 通常のクリックイベント処理を行う
 *
 * @param debounceTime クリック無効期間 (ミリ秒)
 * @param onClickAction クリック時のコールバック
 */
// https://github.com/ApturiCOVID/apturicovid-android/blob/master/app/src/main/java/lv/spkc/apturicovid/extension/View.kt#L29
fun View.setOnDebounceClickListener(debounceTime: Long = 2000L, onClickAction: () -> Unit) {
    setOnClickListener(object : DebouncedClickListener(debounceTime) {
        override fun performClick(v: View) {
            Log.d("setOnDebounceClickListener", "通常のクリックイベント処理を行う")
            onClickAction.invoke()
        }
    })
}