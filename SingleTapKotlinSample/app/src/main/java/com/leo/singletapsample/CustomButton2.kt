package com.leo.singletapsample

import android.content.Context
import android.os.SystemClock
import android.util.AttributeSet
import android.util.Log
import com.google.android.material.button.MaterialButton

/**
 * SingleClick: performClick
 *
 * 2021-06-08 00:12:21.248 13638-13638/leo.singletapsample D/CustomButton2: Enable click.
 * 2021-06-08 00:12:21.248 13638-13638/leo.singletapsample D/MainActivity: CustomButton2 performClick activity
 * 2021-06-08 00:12:22.650 13638-13638/leo.singletapsample D/CustomButton2: Disable click for a certain period.
 * 2021-06-08 00:12:23.318 13638-13638/leo.singletapsample D/CustomButton2: Enable click.
 * 2021-06-08 00:12:23.318 13638-13638/leo.singletapsample D/MainActivity: CustomButton2 performClick activity
 *
 */
class CustomButton2(context: Context, attrs: AttributeSet) : MaterialButton(context, attrs) {

    private var lastClickTime: Long = 0

    /** クリック無効期間. */
    private var clickDisablePeriod: Long = 2000

    /**
     * [android.view.View.performClick]
     * このビューのOnClickListenerを呼び出します.
     * performClickはあくまでOnClickListenerを呼ぶ/呼ばないの制御しかしない.
     *
     * @return true: OnClickイベント発火, false: OnClickイベント発火しない
     */
    override fun performClick(): Boolean {
        // 一定期間、クリックを無効化する.
        if (SystemClock.elapsedRealtime() - lastClickTime < clickDisablePeriod) {
            Log.d("CustomButton2", "Disable click for a certain period.")
            // OnClickListenerを呼び出さない.
            return false
        }
        lastClickTime = SystemClock.elapsedRealtime()

        // 通常のクリックイベント処理を行う.OnClickListenerを呼び出す.
        Log.d("CustomButton2", "Enable click.")
        return super.performClick()
    }

    /**
     *  クリック無効期間を変更可能する.
     */
    fun setClickDisablePeriod(clickDisablePeriod: Long) {
        this.clickDisablePeriod = clickDisablePeriod
    }
}