package ru.skillbranch.devintensive

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.models.Bender

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var etMessage: EditText
    lateinit var ivBender: ImageView
    lateinit var questionTv: TextView
    lateinit var sendButton: ImageView
    lateinit var bender: Bender

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etMessage = et_message
        ivBender = iv_bender
        questionTv = tv_text
        sendButton = iv_send

        val status = savedInstanceState?.getString("STATUS") ?: Bender.Status.NORMAL.name
        val question = savedInstanceState?.getString("QUESTION") ?: Bender.Question.NAME.name

        bender = Bender(Bender.Status.valueOf(status), Bender.Question.valueOf(question))

        val(r,g,b) = bender.status.color
        ivBender.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)

        sendButton.setOnClickListener(this)
        questionTv.text = bender.askQuestion()
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.iv_send) {
            val (phrase, color) = bender.listenAnswer(etMessage.text.toString().toLowerCase().trim())
            val (r, g, b) = color
            ivBender.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)
            questionTv.text = phrase
            etMessage.setText("")
        }
    }


    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString("STATUS", bender.status.name)
        outState?.putString("QUESTION", bender.question.name)
        Log.d("M_MainActivity", "onSaveInstanceState ${bender.status.name} ${bender.question.name}")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("M_MainActivity", "onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.d("M_MainActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("M_MainActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("M_MainActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("M_MainActivity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("M_MainActivity", "onDestroy")
    }
}
