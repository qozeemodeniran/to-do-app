package com.example.myday

import android.content.DialogInterface.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    // items definition
    lateinit var item : EditText
    lateinit var add : Button
    lateinit var listView : ListView

    // array list to hold items
    var itemList = ArrayList<String>()

    // accessing the write and read data object
    var fileHelper = FileHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // items assignment
        item = findViewById(R.id.editText)
        add = findViewById(R.id.button)
        listView = findViewById(R.id.list)

        itemList = fileHelper.readData(this)

        // sending the data to the list view using the array adapter
        var arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, itemList)

        listView.adapter = arrayAdapter

        //  adding click-listener to the ADD TASK button
        add.setOnClickListener {
            var itemName : String = item.text.toString()
            itemList.add(itemName)
            item.setText("")
            fileHelper.writeData(itemList, applicationContext)
            arrayAdapter.notifyDataSetChanged()
        }

        // deleting tasks
        listView.setOnItemClickListener { adapterView, view, posiiton, l ->

            var alert = AlertDialog.Builder(this)
            alert.setTitle("Remove Task")
            alert.setMessage("Do you want to remove task from the list?")
            alert.setCancelable(false)

            // if user clicks No
            alert.setNegativeButton("No", OnClickListener { dialogInterface, i ->
                dialogInterface.cancel()
            })

            // if user clicks Yes
            alert.setPositiveButton("Yes", OnClickListener { dialogInterface, i ->
                itemList.removeAt(posiiton)
                arrayAdapter.notifyDataSetChanged()
                fileHelper.writeData(itemList, applicationContext)
            })

            alert.create().show()
        }
    }
}