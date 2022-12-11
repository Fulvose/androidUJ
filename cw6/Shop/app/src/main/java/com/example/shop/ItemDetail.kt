package com.example.shop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.details_view.*
import kotlinx.android.synthetic.main.card_view_design.*


class ItemDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_view)

        val name = intent.getStringExtra("name")
        item_name.text = name

        val price = intent.getStringExtra("price")
        item_price.text = price


        button.setOnClickListener(){
            Toast.makeText(applicationContext,"Added to cart!", Toast.LENGTH_SHORT).show()

        }
    }
}