package io.github.currygecko.confidencekeeper.usecase

import android.content.Context
import android.content.Intent
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import io.github.currygecko.confidencekeeper.model.AppInformation
import io.github.currygecko.confidencekeeper.model.ShortcutSettings
import io.github.currygecko.confidencekeeper.ui.LauncherActivity

class MakeShortcutUseCase {


    operator fun invoke(
        appInfo: AppInformation,
        shortcutSettings: ShortcutSettings,
        context: Context
    ) {
        val shortcut = buildShortcutInfo(context, appInfo, null)

        if (requestPinShortcut(context)) {
            makePinShortcut(context, shortcut)
        } else {
            ShortcutManagerCompat.addDynamicShortcuts(context, listOf(shortcut))
        }
    }

    private fun requestPinShortcut(context: Context): Boolean {
        return ShortcutManagerCompat.isRequestPinShortcutSupported(context)
    }

    private fun makePinShortcut(context: Context, shortcut: ShortcutInfoCompat) {
        ShortcutManagerCompat.requestPinShortcut(context, shortcut, null)
    }

    private fun buildShortcutInfo(
        context: Context,
        appInfo: AppInformation,
        settings: ShortcutSettings? = null
    ): ShortcutInfoCompat {
        val intent = Intent(context, LauncherActivity::class.java)
        intent.action = Intent.ACTION_VIEW
        intent.putExtra(AppInformation.EXTRA_KEY, appInfo.packageName)
//        settings?.let {
//            intent.putParcelableArrayListExtra("settings", arrayListOf(it))
//        }
        val key = "${context.packageName}_${appInfo.packageName}" //
        return ShortcutInfoCompat.Builder(context, key)
            .setShortLabel(appInfo.packageLabel)
            .setLongLabel(appInfo.packageLabel)
            .setIcon(appInfo.makeIcon(context))
            .setIntent(intent)
            .build()
    }
}