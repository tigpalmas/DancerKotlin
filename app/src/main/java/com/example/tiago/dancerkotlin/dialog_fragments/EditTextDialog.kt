package com.example.tiago.dancerkotlin.dialog_fragments

import android.app.DialogFragment
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tiago.dancerkotlin.R
import android.graphics.drawable.ColorDrawable
import android.widget.EditText
import com.example.tiago.dancerkotlin.domain.Dancer
import kotlinx.android.synthetic.main.dialog_edittext.*
import java.util.ArrayList
import android.app.Activity


/**
 * Created by tiago on 05/01/2018.
 */
class EditTextDialog : DialogFragment() {


    private var mListener: OnCompleteListener? = null
    private var mDancer: Dancer? = null;


    companion object {
        val EXTRA_EVENT = "extra_event"
        fun novaInstancia(dancer: Dancer): EditTextDialog {
            val parametros = Bundle()
            parametros.putSerializable(EXTRA_EVENT, dancer)
            val fragment = EditTextDialog()
            fragment.arguments = parametros
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDancer = arguments?.getSerializable(EXTRA_EVENT) as? Dancer

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.dialog_edittext, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        edt_about.setText(mDancer?.description)
        edt_school.setText(mDancer?.school)
        edt_level.setText(mDancer?.level)
        edt_phone.setText(mDancer?.phone)
        edt_value_hour.setText(mDancer?.priceHour?.toString())


        btn_save.setOnClickListener{
            if(checkErrors()){
                buildDancer()
                mListener?.onComplete(mDancer!!);
                this.dismiss()
            }

        }

    }

    override fun onStart() {
        super.onStart()


        if (dialog != null) {
            dialog.window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

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

    private fun isEmpty(etText: EditText): Boolean {
        return etText.text.toString().trim { it <= ' ' }.length == 0
    }

    private fun checkErrors(): Boolean {
        var error = true
        if (isEmpty(edt_about)) {
            edt_about?.error = "Campo não pode estar vazio"
            error = false
        }
        if (isEmpty(edt_level)) {
            edt_level?.error = "Campo não pode estar vazio"
            error = false
        }
        if (isEmpty(edt_phone)) {
            edt_phone.error = "Campo não pode estar vazio"
            error = false
        }

        if (isEmpty(edt_school)) {
            edt_school?.error = "Campo não pode estar vazio"
            error = false
        }
        if (isEmpty(edt_value_hour)) {
            edt_value_hour?.error = "Campo não pode estar vazio"
            error = false
        }
        return error;
    }

    private fun buildDancer(){
        mDancer?.description = edt_about.text.toString().trim()
        mDancer?.level = edt_level.text.toString().trim()
        mDancer?.priceHour = edt_value_hour.text.toString().trim()
        mDancer?.phone = edt_phone.text.toString().trim()
        mDancer?.school = edt_school.text.toString().trim()
    }

    interface OnCompleteListener {
        fun onComplete(dancer: Dancer)
    }


}

