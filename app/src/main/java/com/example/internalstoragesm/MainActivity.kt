package com.example.internalstoragesm

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException


class MainActivity : AppCompatActivity() {

    private lateinit var fstream: FileOutputStream


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var intent: Intent?
        val uname = findViewById<EditText>(R.id.txtName)
        val pwd = findViewById<EditText>(R.id.txtPwd)
        val saveBtn = findViewById<Button>(R.id.btnSave)
        saveBtn.setOnClickListener {
            val username = """
                ${uname!!.text}
                
                """.trimIndent()
            val password = pwd!!.text.toString()
            try {
                fstream = openFileOutput("user_details", MODE_PRIVATE)
                fstream.write(username.toByteArray())
                fstream.write(password.toByteArray())
                fstream.close()
                Toast.makeText(applicationContext, "Details Saved Successfully", Toast.LENGTH_SHORT).show()
                intent = Intent(this, DetailsActivity::class.java)
                startActivity(intent)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}