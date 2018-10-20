package kztproject.jp.splacounter.preference

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

object PrefsWrapper {
    private lateinit var prefs: SharedPreferences
    private val userIdKey = "user_id"
    private val userNameKey = "user_name"

    fun initialize(context: Context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context)
    }

    var userId: Long
        get() = prefs.getLong(userIdKey, 0)
        set(value) = prefs.edit().putLong(userIdKey, value).apply()

    var userName: String?
        get() = prefs.getString(userNameKey, "")
        set(value) = prefs.edit().putString(userNameKey, value).apply()
}