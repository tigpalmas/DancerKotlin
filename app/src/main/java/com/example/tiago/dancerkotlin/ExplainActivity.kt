package com.example.tiago.dancerkotlin

import agency.tango.materialintroscreen.MaterialIntroActivity
import agency.tango.materialintroscreen.SlideFragmentBuilder
import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle

/**
 * Created by tiago on 19/01/2018.
 */
class ExplainActivity: MaterialIntroActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addSlide(SlideFragmentBuilder()

                .backgroundColor(R.color.slide_1)
                .buttonsColor(R.color.slide_button )
                .title(resources.getString(R.string.slide_1_title))
                .description(getString(R.string.slide_1_description))
                .image(R.drawable.slide_1)
                .build())

        val neededPermission = arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION)
        addSlide(SlideFragmentBuilder()
                .backgroundColor(R.color.slide_2)
                .buttonsColor(R.color.slide_button )
                .title(resources.getString(R.string.slide_2_title))
                .description(getString(R.string.slide_2_description))
                .image(R.drawable.slide_2)
                .neededPermissions(neededPermission)
                .build())

        addSlide(SlideFragmentBuilder()
                .backgroundColor(R.color.slide_3)
                .buttonsColor(R.color.slide_button )
                .title(resources.getString(R.string.slide_3_title))
                .description(getString(R.string.slide_3_description))
                .image(R.drawable.slide_3)
                .build())
    }

    override fun onFinish() {
        super.onFinish()
        startActivity(Intent(this, IntroActivity::class.java))
        this.finish()
    }


}