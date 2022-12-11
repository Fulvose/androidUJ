package com.example.shop

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.models.ItemsViewModel
import com.example.shop.models.CategoriesViewModel

class MainActivity : AppCompatActivity(), ItemClickListener {



    var data = ArrayList<ItemsViewModel>()

    var categories = ArrayList<CategoriesViewModel>()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)

        categories.add(CategoriesViewModel("Vegetables", 1))
        categories.add(CategoriesViewModel("Fruits", 2))
        categories.add(CategoriesViewModel("Meats", 3))


        for (i in 1..20) {
            data.add(ItemsViewModel("Item nr $i   ", "  Price: ${i*10}"))
        }


        val adapter = CustomAdapter(data, this)
        recyclerview.adapter = adapter
        adapter.notifyDataSetChanged()


    }

    override fun ItemClicked(position: Int) {
        val intent = Intent(this, ItemDetail::class.java)
        intent.putExtra("name", data[position].name)
        intent.putExtra("price", data[position].price)
        startActivity(intent)
    }
}