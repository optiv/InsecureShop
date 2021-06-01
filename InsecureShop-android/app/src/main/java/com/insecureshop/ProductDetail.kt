package com.insecureshop

data class ProductDetail(
    var id: Int,
    var name: String,
    var imageUrl: String,
    var price: String,
    var rating: Int,
    var url: String = "https://www.insecureshopapp.com",
    var qty: Int = 0
)