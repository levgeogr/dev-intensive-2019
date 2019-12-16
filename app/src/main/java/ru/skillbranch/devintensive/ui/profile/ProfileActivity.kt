package ru.skillbranch.devintensive.ui.profile

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_profile.*
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.models.Profile
import ru.skillbranch.devintensive.viewmodels.ProfileViewModel

class ProfileActivity : AppCompatActivity() {

    private lateinit var viewModel: ProfileViewModel
    val IS_EDIT_MODE = "IS_EDIT_MODE"
    var isEditMode: Boolean = false
    lateinit var fields: Map<String, TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        initViews(savedInstanceState)
        initViewModel()
    }

    fun saveProfileInfo() {
        Profile(
            firstName = et_first_name.text.toString(),
            lastName = et_last_name.text.toString(),
            about = et_about.text.toString(),
            repository = et_repository.text.toString()
        ).apply { viewModel.saveProfileData(this) }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(IS_EDIT_MODE, isEditMode)
    }

    private fun initViews(savedInstanceState: Bundle?) {
        fields = mapOf(
            "nickname" to tv_nick_name,
            "rank" to tv_rank,
            "firstname" to et_first_name,
            "lastname" to et_last_name,
            "rating" to tv_rating,
            "respect" to tv_respect,
            "about" to et_about,
            "repository" to et_repository
        )

        isEditMode = savedInstanceState?.getBoolean(IS_EDIT_MODE, false) ?: false
        showCurrentMode(isEditMode)

        btn_edit.setOnClickListener {
            if (isEditMode) {
                saveProfileInfo()
            }
            isEditMode = !isEditMode
            showCurrentMode(isEditMode)
        }

        btn_switch_theme.setOnClickListener {
            viewModel.switchTheme()
        }

    }

    fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        viewModel.getProfileData().observe(this, Observer { updateUI(it) })
        viewModel.getTheme().observe(this, Observer { updateTheme(it)  })
    }

    private fun updateUI(profile: Profile) {
        profile.toMap().also {
            for ((k, v) in fields) {
                v.text = it[k].toString()
            }
        }
    }

    private fun updateTheme(mode: Int) {
        delegate.localNightMode = mode
    }
    private fun showCurrentMode(mode: Boolean) {
        val info = fields.filter { setOf("firstname", "lastname", "about", "repository").contains(it.key) }

        for ((_, v) in info) {
            v as EditText
            v.isFocusable = mode
            v.isEnabled = mode
            v.isFocusableInTouchMode = mode
            v.background.alpha = if (mode) 255 else 0
        }

        ic_eye.visibility = if (mode) View.GONE else View.VISIBLE
        wr_about.isCounterEnabled = mode

        with(btn_edit) {
            val colorFilter = if (mode) {
                PorterDuffColorFilter(
                    resources.getColor(R.color.color_accent, theme),
                    PorterDuff.Mode.SRC_IN
                )
            } else {
                null
            }

            val icon = if (mode) {
                resources.getDrawable(R.drawable.ic_save_black_24dp, theme)
            } else {
                resources.getDrawable(R.drawable.ic_edit_black_24dp, theme)
            }

            background.colorFilter = colorFilter
            setImageDrawable(icon)
        }

    }


}
