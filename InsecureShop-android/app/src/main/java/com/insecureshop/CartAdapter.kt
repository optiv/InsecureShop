package com.insecureshop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.insecureshop.databinding.CartItemBinding

class CartAdapter : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    var cartList : List<ProductDetail> = arrayListOf()

    class CartViewHolder(binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var mBinding = binding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context))
        return CartViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val prodDetail = cartList[position]
        Glide.with(holder.mBinding.picture.context).load(prodDetail.imageUrl)
            .into(holder.mBinding.picture)
        holder.mBinding.prodName.text = prodDetail.name
        holder.mBinding.prodPrice.text = "$ " + prodDetail.price
        holder.mBinding.productCount.text = " Qty : " + prodDetail.qty
    }
}