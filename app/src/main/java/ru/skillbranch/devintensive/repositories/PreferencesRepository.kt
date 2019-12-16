package ru.skillbranch.devintensive.repositories

import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatDelegate
import ru.skillbranch.devintensive.App
import ru.skillbranch.devintensive.models.Profile

object PreferencesRepository {

    private const val APP_THEME = "APP_THEME"
    private const val FIRST_NAME = "FIRST_NAME"
    private const val LAST_NAME = "LAST_NAME"
    private const val ABOUT = "ABOUT"
    private const val REPOSITORY = "REPOSITORY"
    private const val RESPECT = "RESPECT"
    private const val RATING = "RATING"

    private val prefs: SharedPreferences by lazy {
        val context = App.appContext()
        PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun getProfile(): Profile {
        val about = prefs.getString(ABOUT, "")

        val pro = Profile(
            firstName = prefs.getString(FIRST_NAME, "")!!,
            lastName = prefs.getString(LAST_NAME, "")!!,
            about = prefs.getString(ABOUT, "")!!,
            repository = prefs.getString(REPOSITORY, "")!!,
            respect = prefs.getInt(RESPECT, 0),
            rating = prefs.getInt(RATING, 0)
        )
        return pro
    }

    fun getTheme() :Int = prefs.getInt(APP_THEME, AppCompatDelegate.MODE_NIGHT_NO)

    fun saveTheme(theme: Int) {
        putValue(APP_THEME to theme)
    }

    fun saveProfile(profile: Profile) {
        with(profile) {
            putValue(FIRST_NAME to firstName)
            putValue(LAST_NAME to lastName)
            putValue(ABOUT to about)
            putValue(REPOSITORY to repository)
            putValue(RESPECT to respect)
            putValue(RATING to rating)
        }
    }

    private fun putValue(pair: Pair<String, Any>) = with(prefs.edit()) {

        val key = pair.first

        when (val value = pair.second) {
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Long -> putLong(key, value)
            is Float -> putFloat(key, value)
            is Boolean -> putBoolean(key, value)
            else -> error("Only primitives can be stored in shared preferences")
        }
        apply()
    }

    fun getAppTheme(): Int = prefs.getInt(APP_THEME, AppCompatDelegate.MODE_NIGHT_NO)
}