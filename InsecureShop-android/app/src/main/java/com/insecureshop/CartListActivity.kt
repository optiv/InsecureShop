package com.insecureshop

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.insecureshop.util.Util
import kotlinx.android.synthetic.main.activity_cart_list.*


class CartListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_list)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        title = "Cart"

        val cartAdapter = CartAdapter()
        recyclerview.layoutManager = LinearLayoutManager(applicationContext)
        recyclerview.adapter = cartAdapter
        cartAdapter.cartList = Util.getCartProduct(this)
        cartAdapter.notifyDataSetChanged()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
