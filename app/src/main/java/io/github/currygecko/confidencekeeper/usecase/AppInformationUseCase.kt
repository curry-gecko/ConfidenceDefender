package io.github.currygecko.confidencekeeper.usecase

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build


class AppInformationUseCase {

    // インストールアプリのリストを取得する
    @SuppressLint("QueryPermissionsNeeded")
    fun getApplicationInfo(context: Context): List<String> {
        val packageManager = context.packageManager
        val flags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            PackageManager.MATCH_ALL
        } else {
            PackageManager.GET_META_DATA
        }

        val packages = packageManager.getInstalledApplications(flags)

        return packages.map {
            it.loadLabel(packageManager).toString()
        }
    }
}