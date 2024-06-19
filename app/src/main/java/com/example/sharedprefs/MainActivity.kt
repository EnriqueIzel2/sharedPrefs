package com.example.sharedprefs

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

  companion object {
    const val NAME = "shared_prefs_file"
    const val KEY_NOTIFICATION = "notification_key"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    setupSharedPref()
  }

  private fun setupSharedPref() {
    val sharedPrefs = getSharedPreferences(NAME, Context.MODE_PRIVATE)
    val notification = sharedPrefs.getBoolean(
      KEY_NOTIFICATION,
      false
    )
    Log.i("mainShared", "setupSharedPref: $notification")

    val editor = sharedPrefs.edit()
    editor.putBoolean(KEY_NOTIFICATION, true)
    editor.apply()

    val notificationTrue = sharedPrefs.getBoolean(KEY_NOTIFICATION, false)
    Log.i("mainShared", "setupSharedPref: $notificationTrue")
  }
}