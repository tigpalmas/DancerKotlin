package com.example.tiago.dancerkotlin.fragments

import agency.tango.materialintroscreen.SlideFragment
import android.app.Activity
import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.tiago.dancerkotlin.R


import com.example.tiago.dancerkotlin.domain.Dancer
import com.example.tiago.dancerkotlin.utils.AppPreferenceTools
import kotlinx.android.synthetic.main.dialog_edittext.*


/**
 * Created by tiago on 10/01/2018.
 */
class FormsDancerFragment : SlideFragment() {

    var finish: Boolean = false

    private var mListener: OnCompleteListener? = null

    private var mDancer: Dancer? = null

    companion object {
        val EXTRA_DANCER = "extra_dancer"
        fun novaInstancia(dancer: Dancer): FormsDancerFragment {
            val parametros = Bundle()
            parametros.putSerializable(EXTRA_DANCER, dancer)
            val fragment = FormsDancerFragment()
            fragment.arguments = parametros
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDancer = arguments?.getSerializable(EXTRA_DANCER) as Dancer
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.dialog_edittext, container, false)
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        edt_about.setText( mDancer?.description)
        edt_level.setText( mDancer?.level)
        edt_phone.setText( mDancer?.phone)
        edt_value_hour.setText( mDancer?.priceHour)
        edt_school.setText( mDancer?.school)
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        try {
            this.mListener = activity as OnCompleteListener
        } catch (e: ClassCastException) {
            throw ClassCastException(activity.toString() + " must implement OnCompleteListener")
        }

    }


    override fun canMoveFurther(): Boolean {
        mDancer?.description = edt_about.text.toString()
        mDancer?.priceHour = edt_value_hour.text.toString()
        mDancer?.level = edt_level.text.toString()
        mDancer?.school = edt_school.text.toString()
        mDancer?.phone = edt_phone.text.toString()

        if(checkErrors()){
            mListener?.onComplete(mDancer!!)
            finish = true
        }

        return finish
    }



    override fun cantMoveFurtherErrorMessage(): String {
        return "Você deve preencher todos os dados"
    }

    override fun backgroundColor(): Int {
        return R.color.slide_4
    }

    override fun buttonsColor(): Int {
        return R.color.slide_button
    }




    private fun checkErrors(): Boolean {
        var error = true
        if (isEmpty(edt_about)) {

            error = false
        }
        if (isEmpty(edt_level)) {

            error = false
        }
        if (isEmpty(edt_phone)) {

            error = false
        }

        if (isEmpty(edt_school)) {

            error = false
        }
        if (isEmpty(edt_value_hour)) {

            error = false
        }

        return error;
    }

    private fun isEmpty(etText: EditText): Boolean {
        return etText.text.toString().trim { it <= ' ' }.length == 0
    }

    interface OnCompleteListener {
        fun onComplete(dancer: Dancer)
    }
}