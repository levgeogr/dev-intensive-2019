package ru.skillbranch.devintensive.ui.profile

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.extensions.hideKeyboard
import ru.skillbranch.devintensive.models.Bender

class ProfileActivity : AppCompatActivity() {
    lateinit var etMessage: EditText
    lateinit var ivBender: ImageView
    lateinit var questionTv: TextView
    lateinit var sendButton: ImageView
    private lateinit var bender: Bender

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val status = savedInstanceState?.getString(STATUS) ?: Bender.Status.NORMAL.name
        val question = savedInstanceState?.getString(QUESTION) ?: Bender.Question.NAME.name

        bender = Bender(Bender.Status.valueOf(status), Bender.Question.valueOf(question))
        setBenderColor(bender.status.color)

        etMessage.setOnEditorActionListener { _, actionId, event ->
            val imeAction = when (actionId) {
                EditorInfo.IME_ACTION_DONE,
                EditorInfo.IME_ACTION_SEND,
                EditorInfo.IME_ACTION_GO -> true
                else -> false
            }

            val keyDownEvent = event?.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN

            if (imeAction or keyDownEvent)
                sendAnswerToBender()

            imeAction or keyDownEvent
        }

        sendButton.setOnClickListener {
            sendAnswerToBender()
        }

        questionTv.text = bender.askQuestion()
    }

    private fun sendAnswerToBender() {
        val userAnswer = etMessage.text.toString().trim()
        hideKeyboard()
        if (userAnswer.isEmpty()) return
        val (phrase, color) = bender.listenAnswer(userAnswer)
        setBenderColor(color)
        questionTv.text = phrase
        etMessage.setText("")
    }

    private fun setBenderColor(color: Triple<Int, Int, Int>) {
        val (r, g, b) = color
        ivBender.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState?.putString(STATUS, bender.status.name)
        outState?.putString(QUESTION, bender.question.name)
        Log.d("M_MainActivity", "onSaveInstanceState ${bender.status.name} ${bender.question.name}")
    }

    // андроид студио со своим волшебным alt+enter не перестаёт удивлять
    companion object {
        const val STATUS = "STATUS"
        const val QUESTION = "QUESTION"
    }

}