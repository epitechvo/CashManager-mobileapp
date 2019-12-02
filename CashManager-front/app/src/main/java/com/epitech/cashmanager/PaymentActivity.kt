package com.epitech.cashmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class PaymentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        var total = intent.getFloatExtra("Total", 0F)

        findViewById<TextView>(R.id.total_payment).setText(total.toString())
    }
}
