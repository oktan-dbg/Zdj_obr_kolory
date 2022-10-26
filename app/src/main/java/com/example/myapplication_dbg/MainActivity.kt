package com.example.myapplication_dbg

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var cameraBtn:Button
    private lateinit var myImage:ImageView
    private val cameraRequestId  = 1222
    private var czerwony = 0
    private var niebieski = 0
    private var zielony = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cameraBtn = findViewById(R.id.zdjecie_btn)
        myImage = findViewById(R.id.zdjecie_img)
        /**get Permission*/
        if (ContextCompat.checkSelfPermission(
                applicationContext, Manifest.permission.CAMERA
            )== PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.CAMERA),
                cameraRequestId
            )
        /**set camera Open*/
        cameraBtn.setOnClickListener {
            val cameraInt = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraInt,cameraRequestId)
        }

        //val capture = findViewById<Button>(R.id.zdjecie_btn);
        val obrot_seek = findViewById<SeekBar>(R.id.obrot_seekBar);
        val przezroczystosc = findViewById<SeekBar>(R.id.przezroczystosc_seekBar);
        val zielony_seek = findViewById<SeekBar>(R.id.zielony_seekBar);
        val czerwony_seek = findViewById<SeekBar>(R.id.czerwony_seekBar);
        val niebieski_seek = findViewById<SeekBar>(R.id.niebieski_seekBar);
        val zdjecie = findViewById<ImageView>(R.id.zdjecie_img)
        //zdjecie.setRotation(180F)
        obrot_seek.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, p2: Boolean) {
                zdjecie.setRotation(progress.toFloat())
                //zdjecie.setRotation(180.0F)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                //TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //TODO("Not yet implemented")
            }

        })

        przezroczystosc.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, p2: Boolean) {
                zdjecie.alpha=progress.toFloat()/100
                //zdjecie.setRotation(180.0F)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                //TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //TODO("Not yet implemented")
            }

        })

        zielony_seek.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, p2: Boolean) {
                val iv = findViewById<View>(R.id.zdjecie_img) as ImageView
                zielony=progress
                iv.setColorFilter(Color.rgb(czerwony, zielony, niebieski), PorterDuff.Mode.LIGHTEN)
                //iv.clearColorFilter()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                //TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //TODO("Not yet implemented")
            }

        })

        czerwony_seek.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, p2: Boolean) {
                val iv = findViewById<View>(R.id.zdjecie_img) as ImageView
                czerwony=progress
                iv.setColorFilter(Color.rgb(czerwony, zielony, niebieski), PorterDuff.Mode.LIGHTEN)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                //TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //TODO("Not yet implemented")
            }

        })

        niebieski_seek.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, p2: Boolean) {
                val iv = findViewById<View>(R.id.zdjecie_img) as ImageView
                niebieski=progress
                iv.setColorFilter(Color.rgb(czerwony, zielony, niebieski), PorterDuff.Mode.LIGHTEN)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                //TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //TODO("Not yet implemented")
            }

        })


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == cameraRequestId){
            /**save to Image In layout*/
            val images: Bitmap = data?.extras?.get("data") as Bitmap
            myImage.setImageBitmap(images)
        }
    }
}
