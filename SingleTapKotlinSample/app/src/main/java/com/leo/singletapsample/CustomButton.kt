package com.leo.singletapsample

import android.content.Context
import android.os.SystemClock
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import com.google.android.material.button.MaterialButton

/**
 * Singletap: onTouchEvent.
 *
 * 2021-06-08 00:11:22.536 13638-13638/leo.singletapsample D/CustomButton: action is ACTION_UP
 * 2021-06-08 00:11:22.550 13638-13638/leo.singletapsample D/MainActivity: CustomButton onTouchEvent activity
 * 2021-06-08 00:11:22.641 13638-13638/leo.singletapsample D/CustomButton: Disable click for a certain period.
 * 2021-06-08 00:11:24.418 13638-13638/leo.singletapsample D/CustomButton: Disable click for a certain period.
 * 2021-06-08 00:11:24.513 13638-13638/leo.singletapsample D/CustomButton: Disable click for a certain period.
 * 2021-06-08 00:11:24.981 13638-13638/leo.singletapsample D/CustomButton: action is ACTION_UP
 * 2021-06-08 00:11:24.994 13638-13638/leo.singletapsample D/MainActivity: CustomButton onTouchEvent activity
 *
 */
class CustomButton(context: Context, attrs: AttributeSet) : MaterialButton(context, attrs) {

    private var lastClickTime: Long = 0
    /** クリック無効期間. */
    private var clickDisablePeriod: Long = 2000

    /**
     * [android.view.View.onTouchEvent]
     *
     * @return true: 消化. false: 消化しないで、後続処理(click event等)を行う.
     */
    override fun onTouchEvent(event: MotionEvent): Boolean {
        // 一定期間、クリックイベント発火させない.
        if (SystemClock.elapsedRealtime() - lastClickTime < clickDisablePeriod) {
            Log.d("CustomButton", "Disable click for a certain period.")
            // trueで、「TouchEventを消化」したことになり、クリックイベント発火させない.
            // true: 消化. false: 消化しないで、後続処理(click event等)を行う.
            return true
        }

        // Viewのタッチアップ後、一定期間onClickを発火させないでダブルタップを抑止する.
        if (event.action == MotionEvent.ACTION_UP) {
            Log.d("CustomButton", "action is ACTION_UP")

            lastClickTime = SystemClock.elapsedRealtime()
        }

        // 通常のタッチイベント処理を行う.
        return super.onTouchEvent(event)
    }

    /**
     *  クリック無効期間を変更可能する.
     */
    fun setClickDisablePeriod(clickDisablePeriod: Long) {
        this.clickDisablePeriod = clickDisablePeriod
    }
}