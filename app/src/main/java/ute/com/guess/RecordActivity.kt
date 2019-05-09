package ute.com.guess

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_record.*
import kotlinx.android.synthetic.main.content_material.*
import kotlinx.android.synthetic.main.content_material.counter

class RecordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)
        val count = intent.getIntExtra("COUNTER", -1)
        counter.setText(count.toString())

        save.setOnClickListener { v ->
            val nick = nickname.text.toString()
            getSharedPreferences("guess", Context.MODE_PRIVATE)
                .edit()
                .putInt("REC_COUNTER", count)
                .putString("REC_NICKNAME", nick)
                .apply()

            val intent = Intent()
            intent.putExtra("NICK", nick)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}
