package ute.com.guess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val secretNumber = SecretNumber()
    val TAG: String = MainActivity::class.java.name;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "Secret:${secretNumber.secret}")

//        btn_ok.setOnClickListener { Toast.makeText(this, "xxxxxxxxx", Toast.LENGTH_LONG).show() }
    }

    fun check(view: View) {
        val n = ed_number.text.toString().toInt()
//        println("number: ${n}")
        Log.d(TAG, "number: ${n}")
        val diff = secretNumber.validate(n)
        var message = when {
            diff < 0 -> getString(R.string.highter)
            diff > 0 -> getString(R.string.lower)
            else -> {
                if (secretNumber.count < 3) getString(R.string.Excellent) + getString(R.string.you_got_it)
                else getString(R.string.you_got_it)
            }
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_title_result))
            .setMessage(message)
            .setPositiveButton(getString(R.string.ok), null)
            .show()

    }

}
