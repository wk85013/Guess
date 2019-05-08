package ute.com.guess

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity;
import kotlinx.android.synthetic.main.activity_main.ed_number

import kotlinx.android.synthetic.main.activity_material.*
import kotlinx.android.synthetic.main.content_material.*

class MaterialActivity : AppCompatActivity() {
    val secretNumber = SecretNumber()
    val TAG: String = MaterialActivity::class.java.name;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)
        setSupportActionBar(toolbar)

        Log.d(TAG, "Secret:${secretNumber.secret}")

        fab.setOnClickListener { view ->
            //            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
            AlertDialog.Builder(this)
                .setTitle("ReplayGame")
                .setMessage("Are U Sure?")
                .setPositiveButton(getString(R.string.ok), { dialog, which ->
                    secretNumber.reset()
                    counter.setText(secretNumber.count.toString())
                    ed_number.setText("")

                })
                .setNeutralButton("Cancel", null)
                .show()
        }

        counter.setText(secretNumber.count.toString())

        val count = getSharedPreferences("guess", Context.MODE_PRIVATE)
            .getInt("REC_COUNTER", -1)
        val nick = getSharedPreferences("guess", Context.MODE_PRIVATE)
            .getString("REC_NICKNAME", null)
        Log.i(TAG, "data: " + count + "/" + nick);

    }

    fun check(view: View) {
        val n = ed_number.text.toString().toInt()
//        println("number: ${n}")
        Log.d(TAG, "number: ${n}")

        val diff = secretNumber.validate(n)

        counter.setText(secretNumber.count.toString())

        val message = when {
            diff < 0 -> getString(R.string.highter)
            diff > 0 -> getString(R.string.lower)
            else -> {
                if (secretNumber.count < 3) getString(R.string.Excellent) + getString(R.string.you_got_it)
                else getString(R.string.you_got_it)
            }
        }
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_title_result))
            .setMessage(message)
            .setPositiveButton(getString(R.string.ok), { dialog, which ->
                if (diff == 0) {
                    val intent = Intent(this, RecordActivity::class.java)
                    intent.putExtra("COUNTER", secretNumber.count)
                    startActivity(intent)
                }
            })
            .show()
    }

}
