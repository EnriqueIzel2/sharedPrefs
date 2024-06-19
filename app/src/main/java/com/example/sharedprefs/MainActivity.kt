package com.example.sharedprefs

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {

  lateinit var tvNotification: TextView

  companion object {
    const val NAME = "shared_prefs_file"
    const val KEY_NOTIFICATION = "notification_key"
  }


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    tvNotification = findViewById(R.id.tv_notification)

    tvNotification.setOnClickListener {
      val intent = SettingsActivity.newIntent(this)
      startActivity(intent)
    }

    setupDefaultSharedPrefs()
  }

  private fun setupDefaultSharedPrefs() {
    val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this)
    val notificationTag = sharedPrefs.getBoolean(KEY_NOTIFICATION, false)

    tvNotification.text = "Notification $notificationTag"

    sharedPrefs.registerOnSharedPreferenceChangeListener(this)
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

  override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
    if (key == KEY_NOTIFICATION) {
      val notification = sharedPreferences?.getBoolean(key, false)
      tvNotification.text = "Notification $notification"
    }
  }

  override fun onDestroy() {
    val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this)
    sharedPrefs.unregisterOnSharedPreferenceChangeListener(this)
    super.onDestroy()
  }
}