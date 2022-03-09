package com.stjohnambulance.m_aidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class HomeFragment : AppCompatActivity() {
    var recyclerView: RecyclerView? = null
    var adapter: ProductAdapter? = null
    var productList = ArrayList<Product>( )
   private var database: FirebaseDatabase? = null
    private var reference: DatabaseReference? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_fragment)

        database = FirebaseDatabase.getInstance()
        reference = database?.getReference("products")

        val firebaseListener = object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                productList.clear()

                var child = snapshot.children
                child.forEach{
                    var products = Product(it.child("img").value.toString(),
                    it.child("name").value.toString(),
                    it.child("price").value.toString())

                    productList.add(products)
                }

                adapter?.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("test", error.message)

            }

        }

        reference?.addValueEventListener(firebaseListener)

        recyclerView = findViewById<RecyclerView>(R.id.recycler)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = GridLayoutManager(this,2,
                GridLayoutManager.VERTICAL,
                false)

//        productList.add(product1)
//        productList.add(product2)
//        productList.add(product3)
//        productList.add(product4)

        adapter = ProductAdapter(productList)
        recyclerView?.adapter = adapter


    }
}