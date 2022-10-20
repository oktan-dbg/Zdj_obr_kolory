package com.example.myapplication_dbg

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var pictureIV : ImageView? = null

    private lateinit var photoFile: File
    lateinit var currentPhotoPath: String
    private val PICTURE_FROM_CAMERA: Int = 1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val capture = findViewById<Button>(R.id.zdjecie_btn);
        val obrot_seek = findViewById<SeekBar>(R.id.obrot_seekBar);
        val przezroczystosc = findViewById<SeekBar>(R.id.przezroczystosc_seekBar);
        val kolor_seek = findViewById<SeekBar>(R.id.kolor_seekBar);
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

        initViews();
        RegisterListeners();
    }
    private fun initViews(){
        pictureIV = findViewById(R.id.zdjecie_img)
    }
    private fun RegisterListeners(){
        //val capture = findViewById<Button>(R.id.zdjecie_btn);
        pictureIV!!.setOnClickListener {
            takePicture()
        }
        //zdjecie = findViewById(R.id.zdjecie_img)
    }
    private fun takePicture(){
        val pictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        photoFile = createImageFile()
        val uri= FileProvider.getUriForFile(this,"com.example.retrofittest.fileprovider", photoFile)
        pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        startActivityForResult(pictureIntent, PICTURE_FROM_CAMERA)
    }

    private fun createImageFile(): File {
        val timeStamp: String= SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File?=getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir).apply{currentPhotoPath = absolutePath}
    }
}
