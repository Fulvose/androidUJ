package com.example.shop

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.details_view.*

class MainActivity : AppCompatActivity(), ItemClickListener {



    var data = ArrayList<ItemsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)


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