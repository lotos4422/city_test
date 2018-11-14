package com.example.maks.city_test

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.category_card.view.*
import kotlinx.android.synthetic.main.nav_drawer_header.*
import kotlinx.android.synthetic.main.nav_drawer_header.view.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        setImage()
    }

    private fun setImage() {
        val header = nav_view.getHeaderView(0)
        val cw = ContextWrapper(applicationContext)
        val directory = cw.getDir("imageDir", Context.MODE_PRIVATE)
        val mypath = File(directory, "profile.jpg")
        if (mypath.length() > 0)
            header.imageView.setImageBitmap(BitmapFactory.decodeFile(mypath.path))
        header.imageView.setOnClickListener {
            ImageDialog().show(supportFragmentManager, ImageDialog::javaClass.name)
        }

    }


    private fun saveToInternalStorage(bitmapImage: Bitmap): String {
        val cw = ContextWrapper(applicationContext)
        val directory = cw.getDir("imageDir", Context.MODE_PRIVATE)
        val mypath = File(directory, "profile.jpg")

        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(mypath)
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                fos!!.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
        return directory.absolutePath
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (data?.data!=null) {
                val cw = ContextWrapper(applicationContext)
                val directory = cw.getDir("imageDir", Context.MODE_PRIVATE)
                val mypath = File(directory, "profile.jpg")

                FileOutputStream(mypath).use {
                    val stream = contentResolver.openInputStream(data.data)
                    it.write(stream.readBytes())
                }
                nav_view.getHeaderView(0).imageView.setImageBitmap(BitmapFactory.decodeFile(mypath.path))

            }
        }
    }

}

