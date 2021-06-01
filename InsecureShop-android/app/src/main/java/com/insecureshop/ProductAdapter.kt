package com.insecureshop

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.insecureshop.databinding.ProductItemBinding
import com.insecureshop.util.Util

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    var productList : List<ProductDetail> = arrayListOf()

    class ProductViewHolder(binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var mBinding = binding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context))
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val prodDetail = productList[position]
        val context = holder.mBinding.root.context
        Glide.with(holder.mBinding.picture.context).load(prodDetail.imageUrl)
            .placeholder(ContextCompat.getDrawable(context, R.mipmap.ic_launcher))
            .into(holder.mBinding.picture)
        holder.mBinding.prodName.text = prodDetail.name
        holder.mBinding.productCount.text = prodDetail.qty.toString()
        holder.mBinding.prodPrice.text = "$ " + prodDetail.price
        holder.mBinding.icAdd.setOnClickListener {
            prodDetail.qty = prodDetail.qty + 1
            holder.mBinding.productCount.text = prodDetail.qty.toString()
            Util.updateProductItem(context, prodDetail)
        }
        holder.mBinding.icRemove.setOnClickListener {
            if (prodDetail.qty > 0) {
                prodDetail.qty = prodDetail.qty - 1
                holder.mBinding.productCount.text = prodDetail.qty.toString()
                Util.updateProductItem(context, prodDetail)
            }
        }
        holder.mBinding.moreInfo.setOnClickListener {

            val intent = Intent("com.insecureshop.action.PRODUCT_DETAIL")
            intent.putExtra("url", prodDetail.url)
            context.sendBroadcast(intent)

        }
    }
}