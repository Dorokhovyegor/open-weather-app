package com.dorokhov.openweatherapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.dorokhov.openweatherapp.R
import com.dorokhov.openweatherapp.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun displayProgressBar(boolean: Boolean) {
        if (boolean) {
            progress_horizontal.visibility = View.VISIBLE
        } else {
            progress_horizontal.visibility = View.GONE
        }
    }
}
