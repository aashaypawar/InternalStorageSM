package com.example.internalstoragesm

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException


/**
 * Created by tutlane on 04-01-2018.
 */
class DetailsActivity : AppCompatActivity() {

    private lateinit var fstream: FileInputStream

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        var intent: Intent?
        val result = findViewById<View>(R.id.resultView) as TextView
        val back = findViewById<View>(R.id.btnBack) as Button
        try {
            fstream = openFileInput("user_details")
            val sbuffer = StringBuffer()
            var i: Int
            while (fstream.read().also { i = it } != -1) {
                sbuffer.append(i.toChar())
            }
            fstream.close()
            val details = sbuffer.toString().split("\n").toTypedArray()
            result.text = """
                Name: ${details[0]}
                Password: ${details[1]}
                """.trimIndent()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        back.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}