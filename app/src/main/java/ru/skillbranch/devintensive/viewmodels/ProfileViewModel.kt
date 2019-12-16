package ru.skillbranch.devintensive.viewmodels

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.skillbranch.devintensive.models.Profile
import ru.skillbranch.devintensive.repositories.PreferencesRepository

class ProfileViewModel : ViewModel() {

    private val repo: PreferencesRepository = PreferencesRepository
    private val profileData = MutableLiveData<Profile>()
    private val appTheme = MutableLiveData<Int>()


    init {
        profileData.value = repo.getProfile()
        appTheme.value = repo.getAppTheme()
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun getProfileData(): LiveData<Profile> = profileData

    fun getTheme() : LiveData<Int> = appTheme

    fun saveProfileData(profile: Profile) {
        repo.saveProfile(profile)
        profileData.value = profile
    }

    fun switchTheme() {
        if (appTheme.value == AppCompatDelegate.MODE_NIGHT_NO) {
            appTheme.value = AppCompatDelegate.MODE_NIGHT_YES
        } else {
            appTheme.value = AppCompatDelegate.MODE_NIGHT_NO
        }

        repo.saveTheme(appTheme.value!!)
    }

}