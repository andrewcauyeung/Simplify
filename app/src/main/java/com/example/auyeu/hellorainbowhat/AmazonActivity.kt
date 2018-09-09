package com.example.auyeu.hellorainbowhat

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import com.google.android.things.contrib.driver.button.Button
import com.google.android.things.contrib.driver.rainbowhat.RainbowHat
import com.google.android.things.pio.Gpio
import java.util.*

class AmazonActivity : Activity() {

    //Set of the items to buy from
   /* val array1 = arrayOf("https://www.amazon.com/gp/product/B00ZV9RDKK", "https://www.amazon.com/dp/B07D4F2P26/", "https://www.amazon.com/dp/B01N32NCPM",
            "https://www.amazon.com/dp/B07BC81BTG", "https://www.amazon.com/gp/product/B00WWBCQCK" )*/


    val array5 = arrayOf("https://www.amazon.com/adidas-Metro-Soccer-Socks-1-Pack/dp/B00SGTAA0M/", "https://www.amazon.com/Wayfarers-Keep-Broken-Lands-Book-ebook/dp/B07GMZW6LS/","https://www.amazon.com/gp/product/B01CAUZUU2/")
    val array10 = arrayOf("https://www.amazon.com/adidas-Metro-Soccer-Socks-1-Pack/dp/B00SGTAA0M/", "https://www.amazon.com/Wayfarers-Keep-Broken-Lands-Book-ebook/dp/B07GMZW6LS/","https://www.amazon.com/gp/product/B01CAUZUU2/", "https://www.amazon.com/JOMOO-Wall-Mounted-Bathroom-Stainless-Accessories/dp/B0746BCBRG/","https://www.amazon.com/2014-Forest-Hills-Drive-Explicit/dp/B00PJHY3PW/","https://www.amazon.com/dp/B07GLCMJG8/")
    val array15 = arrayOf("https://www.amazon.com/dp/B07465843H/", "https://www.amazon.com/adidas-Metro-Soccer-Socks-1-Pack/dp/B00SGTAA0M/", "https://www.amazon.com/Wayfarers-Keep-Broken-Lands-Book-ebook/dp/B07GMZW6LS/","https://www.amazon.com/gp/product/B01CAUZUU2/", "https://www.amazon.com/JOMOO-Wall-Mounted-Bathroom-Stainless-Accessories/dp/B0746BCBRG/","https://www.amazon.com/2014-Forest-Hills-Drive-Explicit/dp/B00PJHY3PW/","https://www.amazon.com/dp/B07GLCMJG8/")
    val array20 = arrayOf("https://www.amazon.com/AmazonBasics-USB-3-1-Type-C-Port/dp/B01MCTET84/" , "https://www.amazon.com/dp/B07465843H/", "https://www.amazon.com/adidas-Metro-Soccer-Socks-1-Pack/dp/B00SGTAA0M/", "https://www.amazon.com/Wayfarers-Keep-Broken-Lands-Book-ebook/dp/B07GMZW6LS/","https://www.amazon.com/gp/product/B01CAUZUU2/", "https://www.amazon.com/JOMOO-Wall-Mounted-Bathroom-Stainless-Accessories/dp/B0746BCBRG/","https://www.amazon.com/2014-Forest-Hills-Drive-Explicit/dp/B00PJHY3PW/","https://www.amazon.com/dp/B07GLCMJG8/")

    //Peripheral button led
    var redLed: Gpio? = null
    var greenLed: Gpio? = null
    var blueLed: Gpio? = null

    //Periperal buttons
    var buttonA : Button? = null
    var buttonB : Button? = null
    var buttonC : Button? = null


    var maxBudget = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.webview)
        var extras = intent.extras
        maxBudget = extras.getInt("maxBudget")
        System.out.println(maxBudget)

        genPage()
    }

    fun backTrigger() {
        finish()
    }

    fun ClosedRange<Int>.random() =
            Random().nextInt((endInclusive + 1) - start) +  start

    fun genPage() {
        val myWebView: WebView = findViewById(R.id.webview)

        myWebView.settings.javaScriptEnabled = true
        myWebView.settings.setJavaScriptCanOpenWindowsAutomatically(true)

        var ran = 0

        var arraySize = 0

        if (maxBudget == 5) {
            arraySize = array5.size -1
            ran = (0..arraySize).random()
            myWebView!!.loadUrl(array5[ran])
        }
        else if (maxBudget == 10) {
            arraySize = array10.size -1
            ran = (0..arraySize).random()
            myWebView!!.loadUrl(array10[ran])
        }
        else if (maxBudget == 15) {
            arraySize = array15.size -1
            ran = (0..arraySize).random()
            myWebView!!.loadUrl(array15[ran])
        }
        else if (maxBudget == 20) {
            arraySize = array20.size -1
            ran = (0..arraySize).random()
            myWebView!!.loadUrl(array20[ran])
        }
        System.out.println(ran)

    }

}
