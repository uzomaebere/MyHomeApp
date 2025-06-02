package ng.tagithub.myhomeapp.data

import android.content.Context
import android.content.SharedPreferences


class PreferenceHelper(context: Context) {

    companion object {
        private const val PREF_NAME = "MyAppPreferences"
        const val PREF_SETUP_COMPLETE = "PREF_SETUP_COMPLETE"
        const val PREF_FIRST_LAUNCH = "PREF_FIRST_LAUNCH"
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private var editor = sharedPreferences.edit()

    fun setFirstLaunchDone(status: Boolean) {
        editor.putBoolean(PREF_FIRST_LAUNCH, status).apply()
    }

    fun isFirstLaunch(): Boolean {
        return sharedPreferences.getBoolean(PREF_FIRST_LAUNCH, false)
    }

    fun isSetupComplete(status: Boolean) {
        editor.putBoolean(PREF_SETUP_COMPLETE, status).apply()
    }

    fun getSetupCompleteStatus(): Boolean {
        return sharedPreferences.getBoolean(PREF_SETUP_COMPLETE, false)
    }


}