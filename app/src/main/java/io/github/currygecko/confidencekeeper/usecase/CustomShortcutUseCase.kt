package io.github.currygecko.confidencekeeper.usecase

import android.content.Context
import android.content.Intent
import android.content.pm.ShortcutManager
import android.util.Log
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import io.github.currygecko.confidencekeeper.model.AppInformation
import io.github.currygecko.confidencekeeper.ui.LauncherActivity

class CustomShortcutUseCase {

    fun makeShortcut(appInfo: AppInformation, context: Context) {
        val shortcutManager = context.getSystemService(ShortcutManager::class.java)

        val intent = Intent(context, LauncherActivity::class.java)
        intent.action = Intent.ACTION_VIEW
        intent.putExtra(
            "packageName",
            appInfo.packageName
        )

        val shortcut = ShortcutInfoCompat.Builder(context, "shortcutId")
            .setShortLabel("Launch Other App")
            .setLongLabel("Launch the other app with specific settings")
            .setIcon(appInfo.makeIcon(context))
            .setIntent(intent)
            .build()
        Log.e("err", "$shortcutManager")
        val result = ShortcutManagerCompat.pushDynamicShortcut(context, shortcut)
        Log.e("err", "$result")
    }
}