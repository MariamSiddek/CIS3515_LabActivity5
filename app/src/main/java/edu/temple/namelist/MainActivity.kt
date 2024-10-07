package edu.temple.namelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    // Change to MutableList for easier modifications
    private lateinit var names: MutableList<String> //edit1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize names as a MutableList
        names = mutableListOf("Kevin Shaply", "Stacey Lou", "Gerard Clear", "Michael Studdard", "Michelle Studdard")

        val spinner = findViewById<Spinner>(R.id.spinner)
        val nameTextView = findViewById<TextView>(R.id.textView)


        /* with (spinner) {
           --> need to be edit adapter = CustomAdapter(names, this@MainActivity)
            onItemSelectedListener = object: OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    p0?.run {
                        nameTextView.text = getItemAtPosition(p2).toString()
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
        }*/
        // edit : Set the adapter
        val adapter = CustomAdapter(names, this)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                p0?.run {
                    nameTextView.text = getItemAtPosition(p2).toString()
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //edit : to be safe from crashing if all is deleted
                nameTextView.text = ""
            }
        }

        findViewById<View>(R.id.deleteButton).setOnClickListener {
            // Check if there is at least one item to delete
            if (names.isNotEmpty()) {
                val positionToDelete = spinner.selectedItemPosition

                // Check if the selected position is valid before removing
                if (positionToDelete in names.indices) {
                    names.removeAt(positionToDelete)
                    // Create a new adapter and set it again to the spinner
                    spinner.adapter = CustomAdapter(names, this)
                    // Optional: Reset the TextView if the list is empty
                    if (names.isEmpty()) {
                        nameTextView.text = ""
                    }
                }
            }
        }
    }
}
