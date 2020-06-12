package com.example.exam7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val jsonString = "[\n" +
            "   [\n" +
            "      {\n" +
            "         \"field_id\":1,\n" +
            "         \"hint\":\"UserName\",\n" +
            "         \"field_type\":\"input\",\n" +
            "         \"keyboard\":\"text\",\n" +
            "         \"required\":false,\n" +
            "         \"is_active\":true,\n" +
            "         \"icon\":\"https://jemala.png\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"field_id\":2,\n" +
            "         \"hint\":\"Email\",\n" +
            "         \"field_type\":\"input\",\n" +
            "         \"required\":true,\n" +
            "         \"keyboard\":\"text\",\n" +
            "         \"is_active\":true,\n" +
            "         \"icon\":\"https://jemala.png\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"field_id\":3,\n" +
            "         \"hint\":\"phone\",\n" +
            "         \"field_type\":\"input\",\n" +
            "         \"required\":true,\n" +
            "         \"keyboard\":\"number\",\n" +
            "         \"is_active\":true,\n" +
            "         \"icon\":\"https://jemala.png\"\n" +
            "      }\n" +
            "   ],\n" +
            "   [\n" +
            "      {\n" +
            "         \"field_id\":4,\n" +
            "         \"hint\":\"Full Name\",\n" +
            "         \"field_type\":\"input\",\n" +
            "         \"keyboard\":\"text\",\n" +
            "         \"required\":true,\n" +
            "         \"is_active\":true,\n" +
            "         \"icon\":\"https://jemala.png\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"field_id\":5,\n" +
            "         \"hint\":\"Jemali\",\n" +
            "         \"field_type\":\"input\",\n" +
            "         \"keyboard\":\"text\",\n" +
            "         \"required\":false,\n" +
            "         \"is_active\":true,\n" +
            "         \"icon\":\"https://jemala.png\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"field_id\":6,\n" +
            "         \"hint\":\"Birthday\",\n" +
            "         \"field_type\":\"chooser\",\n" +
            "         \"required\":false,\n" +
            "         \"is_active\":true,\n" +
            "         \"icon\":\"https://jemala.png\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"field_id\":7,\n" +
            "         \"hint\":\"Gender\",\n" +
            "         \"field_type\":\"chooser\",\n" +
            "         \"required\":\"false\",\n" +
            "         \"is_active\":true,\n" +
            "         \"icon\":\"https://jemala.png\"\n" +
            "      }\n" +
            "   ]\n" +
            "]\n"


    private lateinit var mainAdapter: MainRecyclerViewAdapter
    private lateinit var items: Array<Array<ModelClass>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        btnRegister.setOnClickListener {
            register()
        }

    }

    private fun init() {
        items = Gson().fromJson(jsonString, Array<Array<ModelClass>>::class.java)
        mainRecyclerView.layoutManager = LinearLayoutManager(this)
        mainAdapter = MainRecyclerViewAdapter(items)
        mainRecyclerView.adapter = mainAdapter
    }

    private fun register() {
        val alert = mutableMapOf<Int, String>()
        for (innerArray in items) {
            for (item in innerArray) {
                if (
                    item.field_type == "input" && item.required && (alert[item.field_id] == null || alert[item.field_id]!!.isEmpty())
                ) {
                    Toast.makeText(this, "Please fill ${item.hint}", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
}
