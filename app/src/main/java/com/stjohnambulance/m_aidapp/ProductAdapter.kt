package com.stjohnambulance.m_aidapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ProductAdapter(private var productList:MutableList<Product>):RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ProductViewHolder {
        val layoutView:View = LayoutInflater.from(parent.context).
                inflate(R.layout.product_card_view, parent, false)
        return ProductViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
//        holder.productImage.setImageResource(productList[position].img)
        Picasso.get().load(productList[position].img)
            .into(holder.productImage)
        holder.productTitle.text = (productList[position].title)
        holder.productPrice.text = (productList[position].price)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class ProductViewHolder(view: View):RecyclerView.ViewHolder(view){
        var productImage: ImageView = view.findViewById(R.id.productImage)
        var productTitle: TextView = view.findViewById(R.id.productTitle)
        var productPrice: TextView = view.findViewById(R.id.productPrice)

    }


}