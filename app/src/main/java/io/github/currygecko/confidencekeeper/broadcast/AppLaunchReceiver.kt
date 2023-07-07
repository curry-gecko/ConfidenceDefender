package io.github.currygecko.confidencekeeper.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import io.github.currygecko.confidencekeeper.usecase.AdjustVolumeUseCase

class AppLaunchReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.e("currygecko", "onreceived")
        Log.e("currygecko", intent?.action.toString())
        if (intent?.action == Intent.ACTION_MAIN && context != null) {
            val packageName = intent.data?.schemeSpecificPart
            val targetPackage = "com.twitter.android" // 目的のパッケージ名

            if (packageName == targetPackage) {
                // 音量調整APIを起動する処理を記述
                AdjustVolumeUseCase().invoke(context)
            }
        }
    }
}