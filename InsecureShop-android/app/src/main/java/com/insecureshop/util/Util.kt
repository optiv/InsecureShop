package com.insecureshop.util

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.insecureshop.ProductDetail
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

object Util {

    private fun getUserCreds(): HashMap<String,String> {
        val userCreds = HashMap<String, String>()
        userCreds["shopuser"] = "!ns3csh0p"
        return userCreds
    }

    fun verifyUserNamePassword(username: String, password: String): Boolean {
        if (getUserCreds().containsKey(username)) {
            val passwordValue = getUserCreds()[username]
            return passwordValue.equals(password)
        } else {
            return false
        }
    }

    private fun getProductList(): ArrayList<ProductDetail> {
        val productList = ArrayList<ProductDetail>()
        productList.add(ProductDetail(1, "Laptop", "https://images.pexels.com/photos/7974/pexels-photo.jpg", "80", 1, "https://www.insecureshopapp.com"))
        productList.add(ProductDetail(2, "Hat", "https://images.pexels.com/photos/984619/pexels-photo-984619.jpeg", "10", 2, "https://www.insecureshopapp.com"))
        productList.add(ProductDetail(3, "Sunglasses", "https://images.pexels.com/photos/343720/pexels-photo-343720.jpeg", "10", 4, "https://www.insecureshopapp.com"))
        productList.add(ProductDetail(4, "Watch", "https://images.pexels.com/photos/277390/pexels-photo-277390.jpeg", "30", 4, "https://www.insecureshopapp.com"))
        productList.add(ProductDetail(5, "Camera", "https://images.pexels.com/photos/225157/pexels-photo-225157.jpeg", "40", 2, "https://www.insecureshopapp.com"))
        productList.add(ProductDetail(6, "Perfumes", "https://images.pexels.com/photos/264819/pexels-photo-264819.jpeg", "10", 2, "https://www.insecureshopapp.com"))
        productList.add(ProductDetail(7, "Bagpack", "https://images.pexels.com/photos/532803/pexels-photo-532803.jpeg", "20", 2, "https://www.insecureshopapp.com"))
        productList.add(ProductDetail(8, "Jacket", "https://images.pexels.com/photos/789812/pexels-photo-789812.jpeg", "20", 2, "https://www.insecureshopapp.com"))
        return productList
    }

    fun saveProductList(context: Context, productList: List<ProductDetail> = getProductList()) {
        val productJson = Gson().toJson(productList)
        Prefs.getInstance(context).productList = productJson
    }

    fun getProductsPrefs(context: Context): List<ProductDetail> {
        val products =  Prefs.getInstance(context).productList
        return Gson().fromJson(products, object : TypeToken<List<ProductDetail>>() {}.type)
    }

    fun updateProductItem(context: Context, updateProductDetail: ProductDetail) {
        val productList = getProductsPrefs(context)
        for (productDetail in productList) {
            if (productDetail.id == updateProductDetail.id) {
                productDetail.qty = updateProductDetail.qty
            }
        }
        saveProductList(context, productList)
    }

    fun getCartProduct(context: Context): ArrayList<ProductDetail> {
        val cartList = arrayListOf<ProductDetail>()
        val productList = getProductsPrefs(context)
        for (productDetail in productList) {
            if (productDetail.qty > 0) {
                cartList.add(productDetail)
            }
        }
        return cartList
    }
}