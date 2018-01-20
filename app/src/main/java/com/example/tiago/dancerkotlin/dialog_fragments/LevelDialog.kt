package com.example.tiago.dancerkotlin.dialog_fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tiago.dancerkotlin.R
import com.example.tiago.dancerkotlin.domain.ListStylesObject
import com.example.tiago.dancerkotlin.domain.User
import com.example.tiago.dancerkotlin.utils.AppPreferenceTools
import com.webianks.library.scroll_choice.ScrollChoice
import kotlinx.android.synthetic.main.dialog_cities.*

/**
 * Created by tiago on 05/01/2018.
 */
class LevelDialog : DialogFragment() {


    private var mListener: OnCompleteListener? = null
    private var mCities = arrayListOf<String>()
    private var choice: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mCities.add("Básico")
        mCities.add("Intermediário")
        mCities.add("Avançado")
        mCities.add("Professor")
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.dilaog_level, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        scroll_choice.addItems(mCities, 0)

        scroll_choice.setOnItemSelectedListener(ScrollChoice.OnItemSelectedListener { scrollChoice, position, name ->
            choice = name;
            this.mListener?.onComplete(choice!!);
        })

        btn_save.setOnClickListener {
            this.dismiss()
        }
    }

    override fun onStart() {
        super.onStart()


        if (dialog != null) {
            dialog.window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            //dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        try {
            this.mListener = activity as OnCompleteListener
        } catch (e: ClassCastException) {
            throw ClassCastException(activity.toString() + " must implement OnCompleteListener")
        }

    }



    interface OnCompleteListener {
        fun onComplete(level: String)
    }




}