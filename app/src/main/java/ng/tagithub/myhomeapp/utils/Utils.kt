package ng.tagithub.myhomeapp.utils

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo

fun Context.isDefaultLauncher(): Boolean {
    val intent = Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME)
    val resolved = packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)
    return resolved?.activityInfo?.packageName == packageName
}

fun Context.isAppDefaultLauncher(): Boolean {
    val pm = packageManager
    val homeIntent = Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME)
    val resolveInfo = pm.resolveActivity(homeIntent, PackageManager.MATCH_DEFAULT_ONLY)

    // Ensure it's not null and matches your app's package
    return resolveInfo?.activityInfo?.packageName == applicationContext.packageName
            && isLauncherSetAsPreferred(resolveInfo)
}

// Additional helper to ensure it's set as preferred
private fun Context.isLauncherSetAsPreferred(resolveInfo: ResolveInfo?): Boolean {
    val componentName = resolveInfo?.activityInfo?.let {
        ComponentName(it.packageName, it.name)
    } ?: return false

    val packageManager = packageManager
    val preferredActivities = ArrayList<IntentFilter>()
    val preferredActivitiesInfo = ArrayList<ComponentName>()

    packageManager.getPreferredActivities(
        preferredActivities,
        preferredActivitiesInfo,
        null
    )

    return preferredActivitiesInfo.contains(componentName)
}