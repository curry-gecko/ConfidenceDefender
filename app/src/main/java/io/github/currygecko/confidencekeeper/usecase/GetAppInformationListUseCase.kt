package io.github.currygecko.confidencekeeper.usecase

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import io.github.currygecko.confidencekeeper.model.AppInformation
import io.github.currygecko.confidencekeeper.model.asAppInfo


class GetAppInformationListUseCase {

    // インストールアプリのリストを取得する
    @SuppressLint("QueryPermissionsNeeded")
    operator fun invoke(context: Context): List<AppInformation> {
        val packageManager = context.packageManager
        val flags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            PackageManager.MATCH_ALL
        } else {
            PackageManager.GET_META_DATA
        }

        val packages = packageManager.getInstalledApplications(flags)

        return filterAppInfo(packages).map { appInfo ->
            appInfo.asAppInfo(packageManager)
        }.sortedBy {
            it.packageLabel
        }

    }

    private fun filterAppInfo(packages: List<ApplicationInfo>): List<ApplicationInfo> {
        return packages.filter { appInfo ->
            !appInfo.packageName.startsWith("com.android") &&
                    !appInfo.packageName.startsWith("com.google") &&
                    !appInfo.packageName.startsWith("android")
        }
    }

}