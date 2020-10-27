package fr.supinternet.androidtv.ui

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager
            .beginTransaction()
            .replace(android.R.id.content, ListFragment())
            .commitAllowingStateLoss()

        AlertDialog.Builder(this)
            .setTitle("Mon titre")
            .setMessage("Mon message")
            .setPositiveButton(android.R.string.ok, null)
            .show()
    }
}