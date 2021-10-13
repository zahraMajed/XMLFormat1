package com.example.xmlformat

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream

//step : create MyXmlPullParserHandler class
//by this class i will return an array list of studentData object by parsing an XML file as inputStream
//i will get the data required for studentData class by parsing XML file then return array list of studentData objec

class MyXmlPullParserHandler {
    private var stdName = ""
    private var stdMark= 0

    val studentsList= ArrayList<studentData>()
    var text=""

    fun parse(inputStream: InputStream): ArrayList<studentData> {

        try {

            /*get parser:
            parser jobs is walking through and analyzing xml data file*/
            val factory = XmlPullParserFactory.newInstance()
            val parser = factory.newPullParser()
            parser.setInput(inputStream, null) // here the input stream should be the xml file name (call in main activity)

            /*get parser eventType:
            it means where is my parser right now in the xml file, is it in start tag? end tag?ect
            Returns the type of the current event (START_TAG, END_TAG, TEXT, etc.)*/
            //^^^^^^^^first eventType will be start tag^^^^^^^^
            var eventType = parser.eventType

            /*loop in the file until you reach the end of it
            to get event we should use XmlPullParser.event

            the concept of loop:
            walinkg through file and save tag name if start tag or end tag then
             if event = text, store the text element in text variable and go to next event
             if event end tag, check tag name
             if tag name =name then put text variable in StdName
             if tag name =mark then put text variable in StdMark
             if tag name =Student then make studentData object and add it to array list */

            while (eventType != XmlPullParser.END_DOCUMENT){

                //^^^^^^^^since eventType=start tag,parser.name=<Student>^^^^^^^^
                val tagName= parser.name

                when (eventType){
                    //^^^^^^^^since eventType=start tag and there is no option for it, it'll skip when code part^^^^^^^^
                    /* 1) if event type=text, we'll get text element and store it in text*/
                    XmlPullParser.TEXT -> text = parser.text
                    /* 2) if event type=end tag, check tag name*/
                    XmlPullParser.END_TAG -> when {
                        tagName.equals("name", ignoreCase = true) -> {
                            stdName= text
                        }
                        tagName.equals("marks", ignoreCase = true) -> {
                            stdMark=text.toInt()
                        }
                        tagName.equals("Student", ignoreCase = true) -> {
                            studentsList.add(studentData(stdName,stdMark))
                        }
                    }
                }// end when

                //Go to next event in the file
                //^^^^^^^^after this line eventType will be start tag too but not for <Student> tag this time,
                //it'll be for the next event which is start tag for <name>^^^^^^^^
                eventType=parser.next()
            }//end while()
        }//end try

        //these tow exceptions are important to add
        catch (e: XmlPullParserException){
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return studentsList
    }//end parse()

    //patser.evenvTupe: Returns the type of the current event
    //parser.name: For START_TAG or END_TAG events, the (local) name of the current element is returned
    //parser.text: Returns the element (text) content as String.
    //parser.next(): Get next parsing event

}//end class