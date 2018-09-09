package com.example.auyeu.hellorainbowhat

import android.app.Activity
import android.os.Bundle
import android.webkit.WebView
import com.google.android.things.contrib.driver.button.Button
import com.google.android.things.pio.Gpio
import java.util.*

class GrubHubActivity : Activity() {

    val array5 = arrayOf("https://www.grubhub.com/restaurant/wawa-1900-market-st-philadelphia/591905/", "https://www.grubhub.com/restaurant/mood-indian-423-w-girard-ave-philadelphia/274535/","https://www.grubhub.com/restaurant/pho-20-234-n-10th-st-philadelphia/340573/")
    val array10 = arrayOf("https://www.grubhub.com/restaurant/wawa-1900-market-st-philadelphia/591905/", "https://www.grubhub.com/restaurant/mood-indian-423-w-girard-ave-philadelphia/274535/","https://www.grubhub.com/restaurant/pho-20-234-n-10th-st-philadelphia/340573/", "https://us.orderspoon.com/playabowlscookman/","https://www.grubhub.com/restaurant/byblos-116-south-18th-street-philadelphia/328359/","https://www.grubhub.com/restaurant/butcher-burger-1016-race-st-ste-18-philadelphia/847628/")
    val array15 = arrayOf("https://www.grubhub.com/restaurant/lale-turkish-cuisine-216-south-st-philadelphia/78628", "https://www.grubhub.com/restaurant/wawa-1900-market-st-philadelphia/591905/", "https://www.grubhub.com/restaurant/mood-indian-423-w-girard-ave-philadelphia/274535/","https://www.grubhub.com/restaurant/pho-20-234-n-10th-st-philadelphia/340573/", "https://us.orderspoon.com/playabowlscookman/","https://www.grubhub.com/restaurant/byblos-116-south-18th-street-philadelphia/328359/","https://www.grubhub.com/restaurant/butcher-burger-1016-race-st-ste-18-philadelphia/847628/")
    val array20 = arrayOf("https://www.grubhub.com/restaurant/savas-brick-oven-pizza-3505-lancaster-ave-philadelphia/316420/", "https://www.grubhub.com/restaurant/lale-turkish-cuisine-216-south-st-philadelphia/78628", "https://www.grubhub.com/restaurant/wawa-1900-market-st-philadelphia/591905/", "https://www.grubhub.com/restaurant/mood-indian-423-w-girard-ave-philadelphia/274535/","https://www.grubhub.com/restaurant/pho-20-234-n-10th-st-philadelphia/340573/", "https://us.orderspoon.com/playabowlscookman/","https://www.grubhub.com/restaurant/byblos-116-south-18th-street-philadelphia/328359/","https://www.grubhub.com/restaurant/butcher-burger-1016-race-st-ste-18-philadelphia/847628/")

    //Peripheral button led
    var redLed: Gpio? = null
    var greenLed: Gpio? = null
    var blueLed: Gpio? = null

    //Periperal buttons
    var buttonA: Button? = null
    var buttonB: Button? = null
    var buttonC: Button? = null


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
            Random().nextInt((endInclusive + 1) - start) + start

    fun genPage() {
        val myWebView: WebView = findViewById(R.id.webview)

        myWebView.settings.javaScriptEnabled = true
        myWebView.settings.setJavaScriptCanOpenWindowsAutomatically(true)

        var ran = 0

        var arraySize = 0

        if (maxBudget == 5) {
            arraySize = array5.size - 1
            ran = (0..arraySize).random()
            myWebView!!.loadUrl(array5[ran])
        } else if (maxBudget == 10) {
            arraySize = array10.size - 1
            ran = (0..arraySize).random()
            myWebView!!.loadUrl(array10[ran])
        } else if (maxBudget == 15) {
            arraySize = array15.size - 1
            ran = (0..arraySize).random()
            myWebView!!.loadUrl(array15[ran])
        } else if (maxBudget == 20) {
            arraySize = array20.size - 1
            ran = (0..arraySize).random()
            myWebView!!.loadUrl(array20[ran])
        }
        System.out.println(ran)

    }
}
