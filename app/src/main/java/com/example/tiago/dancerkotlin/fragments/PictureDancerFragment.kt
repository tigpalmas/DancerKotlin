package com.example.tiago.dancerkotlin.fragments

import agency.tango.materialintroscreen.SlideFragment
import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tiago.dancerkotlin.R
import com.example.tiago.dancerkotlin.utils.AppPreferenceTools
import com.example.tiago.dancerkotlin.utils.Util
import com.nguyenhoanglam.imagepicker.model.Config
import com.nguyenhoanglam.imagepicker.model.Image
import com.nguyenhoanglam.imagepicker.ui.imagepicker.ImagePicker
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_picture_dancer.*


/**
 * Created by tiago on 10/01/2018.
 */
class PictureDancerFragment : SlideFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_picture_dancer, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val mPreference = Util.getAppPreference(activity)
        val user = mPreference?.getUser()
        Picasso.with(activity).load(user?.imageURL).into(img_user)

        btn_load_picture.setOnClickListener{
            ImagePicker.with(this)                         //  Initialize ImagePicker with activity or fragment context
                    .setToolbarColor("#212121")         //  Toolbar color
                    .setStatusBarColor("#000000")       //  StatusBar color (works with SDK >= 21  )
                    .setToolbarTextColor("#FFFFFF")     //  Toolbar text color (Title and Done button)
                    .setToolbarIconColor("#FFFFFF")     //  Toolbar icon color (Back and Camera button)
                    .setProgressBarColor("#4CAF50")     //  ProgressBar color
                    .setBackgroundColor("#212121")      //  Background color
                    .setFolderMode(true)                //  Folder mode
                    .setLimitMessage("Você deve selecionar somente uma imagem")    // Selection limit message
                    .setMaxSize(1)                     //  Max images can be selected
                    .setSavePath("ImagePicker")         //  Image capture folder name
                    .setKeepScreenOn(true)              //  Keep screen on when selecting images
                    .start();
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == Config.RC_PICK_IMAGES && resultCode == Activity.RESULT_OK && data !=null){
            val images = data.getParcelableArrayListExtra<Image>(Config.EXTRA_IMAGES)
            val image = images.get(0)
            Log.i("teste", image.path)
              val bmImg = BitmapFactory.decodeFile(image.path)
              img_background.setImageBitmap(bmImg)
        }

        super.onActivityResult(requestCode, resultCode, data)


    }




    override fun canMoveFurther(): Boolean {
      return true
    }

    override fun cantMoveFurtherErrorMessage(): String {
        return activity.resources.getString(R.string.slide_4_checkbox_error)
    }

    override fun backgroundColor(): Int {
        return R.color.slide_3
    }

    override fun buttonsColor(): Int {
        return R.color.slide_button
    }

}