package io.github.currygecko.confidencekeeper.usecase

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import io.github.currygecko.confidencekeeper.model.AppInfo
import io.github.currygecko.confidencekeeper.model.asAppInfo


class AppInformationUseCase {

    // インストールアプリのリストを取得する
    @SuppressLint("QueryPermissionsNeeded")
    fun getApplicationInfo(context: Context): List<AppInfo> {
        val packageManager = context.packageManager
        val flags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            PackageManager.MATCH_ALL
        } else {
            PackageManager.GET_META_DATA
        }

        val packages = packageManager.getInstalledApplications(flags)

        return packages.filter { appInfo ->
            !appInfo.packageName.startsWith("com.android") &&
                    !appInfo.packageName.startsWith("com.google")
        }.map { appInfo ->
            appInfo.asAppInfo(packageManager)
        }

    }
}