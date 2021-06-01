package com.insecureshop

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.insecureshop.broadcast.ProductDetailBroadCast
import com.insecureshop.util.Prefs
import com.insecureshop.util.Util
import kotlinx.android.synthetic.main.activity_product_list.*


class ProductListActivity : AppCompatActivity() {
    private val productDetailBroadCast = ProductDetailBroadCast()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (TextUtils.isEmpty(Prefs.getInstance(applicationContext).username)) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            return
        }
        setContentView(R.layout.activity_product_list)
        setSupportActionBar(toolbar)

        val intentFilter = IntentFilter("com.insecureshop.action.PRODUCT_DETAIL")
        registerReceiver(productDetailBroadCast, intentFilter)
        val productAdapter = ProductAdapter()
        recyclerview.layoutManager = GridLayoutManager(applicationContext, 2)
        recyclerview.adapter = productAdapter
        productAdapter.productList = Util.getProductsPrefs(this)
        productAdapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        if (item.itemId == R.id.logout) {
            Prefs.getInstance(applicationContext).clearAll()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        if (item.itemId == R.id.cart) {
            val intent = Intent(this, CartListActivity::class.java)
            startActivity(intent)
        }
        if(item.itemId == R.id.about){
            val intent = Intent(this, AboutUsActivity::class.java)
            startActivity(intent)
        }
        return true
    }

}
