package com.example.tiago.dancerkotlin.fragments

import agency.tango.materialintroscreen.SlideFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tiago.dancerkotlin.R
import com.example.tiago.dancerkotlin.domain.User
import com.example.tiago.dancerkotlin.utils.AppPreferenceTools
import com.example.tiago.dancerkotlin.utils.Util

/**
 * Created by tiago on 10/01/2018.
 */
class CompleteFragment : SlideFragment() {
    lateinit var mPreference: AppPreferenceTools
    lateinit var mUser: User


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPreference = Util.getAppPreference(activity)
        mUser = mPreference.getUser()
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.activity_dancer, container, false)
    }



    override fun canMoveFurther(): Boolean {

        return false
    }

    override fun cantMoveFurtherErrorMessage(): String {
        return activity.resources.getString(R.string.slide_4_checkbox_error)
    }

    override fun backgroundColor(): Int {
        return R.color.slide_4
    }

    override fun buttonsColor(): Int {
        return R.color.slide_button
    }



}