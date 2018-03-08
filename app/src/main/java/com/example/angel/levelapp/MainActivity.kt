package com.example.angel.levelapp

import android.content.Context
import android.content.pm.ActivityInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SensorEventListener
{
    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        if (p0 != null)
        {
            textView1.text = "X: ${p0.values[0]}"
            textView2.text = "y: ${p0.values[1]}"
            textView3.text = "z: ${p0.values[2]}"
        }
    }

    private var mSensorManager : SensorManager ?= null
    private var mAccelerometer : Sensor ?= null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        //requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

    }

    override fun onResume()
    {
        super.onResume()
        mSensorManager!!.registerListener(this,mAccelerometer,
                                          SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onPause()
    {
        super.onPause()
        mSensorManager!!.unregisterListener(this)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        textView4.text = "X: ${event?.x}"
        textView5.text = "y: ${event?.y}"
        return true
    }
}
