package com.epitech.cashmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

lateinit var productAdapter: ProductAdapter
lateinit var cartAdapter: CartAdapter
lateinit var products: ArrayList<Product>
lateinit var user: User
lateinit var cart: MutableList<Product>
lateinit var dialogView: View
lateinit var recyclerView: RecyclerView

class ProductListActivity : AppCompatActivity(), View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        products = intent.getParcelableArrayListExtra(LoginActivity.EXTRA_PRODUCTS_LIST)
        user = intent.getParcelableExtra(LoginActivity.EXTRA_USER)
        cart = user.cart.toMutableList()


        cartAdapter = CartAdapter(cart, this)
        productAdapter = ProductAdapter(products, this)

        val recyclerView = findViewById<RecyclerView>(R.id.products_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = productAdapter

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_products_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_show_cart -> {
                showCart(cart)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun showCart(products: List<Product>) {
        dialogView = LayoutInflater.from(this).inflate(R.layout.cart_products, null)
        recyclerView = dialogView.findViewById<RecyclerView>(R.id.cart_recycler_view)
        cartAdapter = CartAdapter(products, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = cartAdapter

        countTotal(products)

        val builder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle("Cart's products")
        val alertDialog = builder.show()
    }

    override fun onClick(view: View) {
        when(view.id) {
            R.id.item -> {
                cart.add(products[view.tag as Int])
                user.cart = cart.toTypedArray()

            }
            R.id.item_cart -> {
                cart.removeAt(view.tag as Int)
                user.cart = cart.toTypedArray()
                countTotal(cart)
            }
        }
        cartAdapter.notifyDataSetChanged()

    }

    private fun countTotal(products: List<Product>) {
        val total = dialogView.findViewById<TextView>(R.id.total)
        var totalF = 0F
        for (product in products) {
            totalF += product.price
        }
        total.text = totalF.toString()
    }
}
