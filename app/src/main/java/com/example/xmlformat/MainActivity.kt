package com.example.xmlformat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    var studentList= ArrayList<studentData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            val parser = MyXmlPullParserHandler()
            val iStream= assets.open("students.xml")
            studentList=parser.parse(iStream)
            rv_main.adapter=RecyclerAdapter(studentList)
            rv_main.layoutManager=LinearLayoutManager(this)

        }catch (e: IOException) {
            println("ISSUE: $e")
        }
    }
}