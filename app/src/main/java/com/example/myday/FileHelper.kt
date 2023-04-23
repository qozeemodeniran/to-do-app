package com.example.myday

import android.content.Context
import java.io.*

// class to help save added task to file
class FileHelper {

    // define a filename
    val FILENAME = "listinfo.dat"

    // method to help save the file
    fun writeData(item : ArrayList<String>, context : Context)
    {
        // creating object to create the file in the device memory
        var fos : FileOutputStream = context.openFileOutput(FILENAME, Context.MODE_PRIVATE)

        // creating object to open the file from the device memory
        var oas = ObjectOutputStream(fos)
        oas.writeObject(item)

        // closing the file
        oas.close()

    }

    // method for reading the file
    fun readData(context: Context) : ArrayList<String>
    {
        var itemList : ArrayList<String>

        // create a try-catch block so the app won't crash
        try {

            var fis : FileInputStream =  context.openFileInput(FILENAME)
            var ois = ObjectInputStream(fis)
            itemList = ois.readObject() as ArrayList<String>

        } catch (e : FileNotFoundException) {
            itemList = ArrayList()
        }


        return itemList
    }

}