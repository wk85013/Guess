package ute.com.guess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import javax.security.auth.login.LoginException

class MainActivity : AppCompatActivity() {
    val secretNumber = SecretNumber()
    val className: String = MainActivity::class.java.name;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(className, "Secret:${secretNumber.secret}")

//        btn_ok.setOnClickListener { Toast.makeText(this, "xxxxxxxxx", Toast.LENGTH_LONG).show() }
    }

    fun check(view: View) {
        val n = ed_number.text.toString().toInt()
//        println("number: ${n}")
        Log.d(className, "number: ${n}")
        val message = when {
            secretNumber.validate(n) < 0 -> "highter"
            secretNumber.validate(n) > 0 -> "lower"
            else -> "you got it"
        }

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

        AlertDialog.Builder(this)
            .setTitle("Result")
            .setMessage(message)
            .setPositiveButton("OK",null)
            .show()
    }
}

