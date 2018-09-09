package com.example.auyeu.hellorainbowhat

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import com.google.android.things.contrib.driver.rainbowhat.RainbowHat
import com.google.android.things.pio.Gpio
import com.google.android.things.contrib.driver.pwmspeaker.Speaker
import com.google.android.things.contrib.driver.apa102.Apa102
import android.view.View
import com.google.android.things.contrib.driver.button.Button
import android.content.Intent
import android.widget.Spinner
import android.widget.AdapterView
import com.google.android.things.contrib.driver.ht16k33.Ht16k33
import java.net.URL


/*
import com.chisw.iotdemo.things.supplier.digitdisplay.DigitDisplaySupplierImpl
import com.chisw.iotdemo.things.controller.digitdisplay.DigitDisplayController
*/


private val TAG = MainActivity::class.java.simpleName

class MainActivity : Activity() {

    var buzzer: Speaker? = null
    var ledstrip: Apa102? = null

    //Peripheral button led
    var redLed: Gpio? = null
    var greenLed:Gpio? = null
    var blueLed: Gpio? = null

    //Periperal buttons
    var buttonA : Button? = null
    var buttonC : Button? = null

    //Max budget
    var maxBudget = 5

    val segment = RainbowHat.openDisplay()

    //Option chosen
    var select : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        //var button = RainbowHat.openButtonA()
        //handlerButtons(button)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.layout)

        initAlphaDisplay()

        initButtons()

        val spinner = findViewById<Spinner>(R.id.spinner)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                select = parent!!.getItemAtPosition(position).toString()
                println(select)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }

        //digitDisplayController?.display(DEFAULT_MESSAGE)

    }

    fun ledstripTrigger(v: View) {
        ledStripController()

        launchActivity()
    }

    fun initAlphaDisplay() {
        segment.setBrightness(Ht16k33.HT16K33_BRIGHTNESS_MAX)
        segment.display(maxBudget);
        segment.setEnabled(true)
    }

    //Controls the rgb strip on RainbowHat
    fun ledStripController(){
        //test ledstrip
        val ledstrip = RainbowHat.openLedStrip()
        ledstrip.brightness = 31

        val rainbow = IntArray(RainbowHat.LEDSTRIP_LENGTH)

        //Loop through entire the led string
        for (x in rainbow.indices) {

            //Set the color of led at x
            rainbow[x] = Color.HSVToColor(255, floatArrayOf(x * 360f / rainbow.size, 1.0f, 1.0f))

            ledstrip.write(rainbow)
            Thread.sleep(1000)

            //Setting to turnoff all LEDs
            for (i in rainbow.indices) {
                rainbow[i] = Color.HSVToColor(255, floatArrayOf(0.0F, 0.0F, 0.0F))
            }

            ledstrip.write(rainbow)
        }
        ledstrip.close()

        //initButtons()
    }

    fun launchActivity() {
        if (select.equals("Amazon")) {
            val intent = Intent(this, AmazonActivity::class.java)
            var b = Bundle()
            b.putInt("maxBudget", maxBudget)
            intent.putExtras(b)
            startActivity(intent)
        }
        else if (select.equals("GrubHub")) {
            val intent = Intent(this, GrubHubActivity::class.java)
            var b = Bundle()
            b.putInt("maxBudget", maxBudget)
            intent.putExtras(b)
            startActivity(intent)
        }
    }

    fun initButtons() {
        redLed = RainbowHat.openLedRed()
        redLed!!.value = true

        greenLed = RainbowHat.openLedGreen()
        greenLed!!.value = false

        blueLed = RainbowHat.openLedBlue()
        blueLed!!.value = true

        buttonA = RainbowHat.openButtonA()
        buttonA!!.setOnButtonEventListener{button, pressed->
            redLed!!.value = pressed

            if (maxBudget != 5 && maxBudget <= 20) {
                maxBudget = maxBudget - 5
                segment.display(maxBudget)
            }
        }

        buttonC = RainbowHat.openButtonC()
        buttonC!!.setOnButtonEventListener { button, pressed ->
            blueLed!!.value = pressed
            if (maxBudget >= 5 && maxBudget != 20) {
                maxBudget = maxBudget + 5
                segment.display(maxBudget)
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()

        buzzer?.stop()

        ledstrip?.close()
    }

}
