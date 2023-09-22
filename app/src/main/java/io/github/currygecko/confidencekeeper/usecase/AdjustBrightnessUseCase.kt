package io.github.currygecko.confidencekeeper.usecase

import android.content.Context
import android.provider.Settings
import android.widget.Toast
import kotlin.math.roundToInt

class AdjustBrightnessUseCase {

    operator fun invoke(context: Context, brightness: Int) {
        // brightnessの値は0から255の範囲であることを想定しています。
        val settingBrightness = (255 * (brightness.toDouble() / 100.0)).roundToInt()

        // システムの明るさ設定を変更
        // 注意: この操作にはWRITE_SETTINGSパーミッションが必要です。
        if (Settings.System.canWrite(context)) {
            Settings.System.putInt(
                context.contentResolver,
                Settings.System.SCREEN_BRIGHTNESS,
                settingBrightness
            )
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }
}
