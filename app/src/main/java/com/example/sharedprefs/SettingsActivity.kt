package com.example.sharedprefs

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_settings)
  }

  companion object {
    fun newIntent(context: Context) : Intent {
      return Intent(context, SettingsActivity::class.java)
    }
  }
}