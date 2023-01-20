package com.example.shop

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.models.ItemsViewModel
import com.example.shop.models.CategoriesViewModel
import junit.framework.Assert.assertEquals
import org.junit.Test
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity(), ItemClickListener {

    private var data = ArrayList<ItemsViewModel>()

    var categories = ArrayList<CategoriesViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)

        var url = URL("http://10.0.2.2:8080/categories")

        with(url.openConnection() as HttpURLConnection) {
            requestMethod = "GET"  // optional default is GET

            inputStream.bufferedReader().use {
                it.lines().forEach { line ->
                    val parts = line.split("")
                    categories.add(CategoriesViewModel(parts[0], parts[1].toInt()))
                }
            }
        }

        url = URL("http://10.0.2.2:8080/products")

        with(url.openConnection() as HttpURLConnection) {
            requestMethod = "GET"  // optional default is GET

            inputStream.bufferedReader().use {
                it.lines().forEach { line ->
                    val parts = line.split("")
                    data.add(ItemsViewModel(parts[0], parts[1]))
                }
            }
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

