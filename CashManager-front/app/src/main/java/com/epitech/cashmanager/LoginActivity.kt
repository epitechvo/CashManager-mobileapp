package com.epitech.cashmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class LoginActivity : AppCompatActivity() {

    companion object{
        val EXTRA_USER = "user"
        val EXTRA_PRODUCTS_LIST = "products"
        val EXTRA_PRODUCT = "product"
        val EXTRA_PRODUCT_INDEX = "productIndex"
    }

    lateinit var usernameInput: TextView
    lateinit var passwordInput: TextView
    lateinit var loginBtn: Button
    lateinit var products: ArrayList<Product>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usernameInput = findViewById<TextView>(R.id.username)
        passwordInput = findViewById<TextView>(R.id.password)
        loginBtn = findViewById<Button>(R.id.login_btn)

        products = arrayListOf(
            Product("produit1", "description 1", 10F ),
            Product("produit2", "description 2", 100.5F ),
            Product("produit3", "description 3", 99.99F )
        )


        loginBtn.setOnClickListener { logIn() }

        findViewById<TextView>(R.id.register_text)
            .setOnClickListener{ startActivity(Intent(this, RegisterActivity::class.java)) }
    }

    fun logIn() {
        val intent = Intent(this, ProductListActivity::class.java)
        val user = User(usernameInput.text.toString(), passwordInput.text.toString())
        intent.putExtra(EXTRA_USER, user)
        intent.putParcelableArrayListExtra(EXTRA_PRODUCTS_LIST, products)
        startActivity(intent)
    }


}
