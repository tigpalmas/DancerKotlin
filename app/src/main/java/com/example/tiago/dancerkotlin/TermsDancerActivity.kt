package com.example.tiago.dancerkotlin


import agency.tango.materialintroscreen.MaterialIntroActivity
import android.Manifest

import android.Manifest.permission

import android.R.attr.description
import android.os.Bundle
import com.example.tiago.dancerkotlin.dialog_fragments.StylesDialogFragment
import com.example.tiago.dancerkotlin.fragments.FormsDancerFragment
import com.example.tiago.dancerkotlin.fragments.ListStylesFragment
import com.example.tiago.dancerkotlin.fragments.TermsConditionsFragment


class TermsDancerActivity : MaterialIntroActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addSlide(TermsConditionsFragment())


    }


}
