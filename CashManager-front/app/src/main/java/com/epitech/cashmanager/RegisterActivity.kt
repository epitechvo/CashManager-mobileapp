package com.epitech.cashmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        findViewById<TextView>(R.id.login_text)
            .setOnClickListener { startActivity(Intent(this, LoginActivity::class.java)) }
    }
}
