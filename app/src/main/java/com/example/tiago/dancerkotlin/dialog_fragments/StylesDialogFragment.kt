package com.example.tiago.dancerkotlin.dialog_fragments

import android.app.Activity
import android.app.DialogFragment
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.example.tiago.dancerkotlin.R
import com.example.tiago.dancerkotlin.adapters.ProductFilterRecyclerViewAdapter
import com.example.tiago.dancerkotlin.domain.Dancer
import com.example.tiago.dancerkotlin.domain.ListStylesObject
import com.example.tiago.dancerkotlin.domain.Style
import kotlinx.android.synthetic.main.dialog_styles.*

/**
 * Created by tiago on 05/01/2018.
 */
class StylesDialogFragment : DialogFragment() {


    private var mListener: OnCompleteListener? = null
    private var mStyles: ListStylesObject? = null;


    companion object {
        val EXTRA_STYLES = "extra_styles"
        fun novaInstancia(styles: ListStylesObject): StylesDialogFragment {
            val parametros = Bundle()
            parametros.putSerializable(EXTRA_STYLES, styles)
            val fragment = StylesDialogFragment()
            fragment.arguments = parametros
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mStyles = arguments?.getSerializable(EXTRA_STYLES) as? ListStylesObject
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.dialog_styles, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        rv_styles!!.layoutManager = LinearLayoutManager(activity,  LinearLayoutManager.VERTICAL, false)
        rv_styles.setHasFixedSize(true);
        val adapter = ProductFilterRecyclerViewAdapter(mStyles?.styles!!, activity);
        rv_styles.adapter = adapter;

        btn_save.setOnClickListener {
            val listObject = ListStylesObject()
            listObject.styles = adapter.selectedList
            this.mListener?.onComplete(listObject);
            this.dismiss()
        }

        btn_cancel.setOnClickListener{
            this.dismiss()
        }

    }

    override fun onStart() {
        super.onStart()


        if (dialog != null) {
            dialog.window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
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
        fun onComplete(list: ListStylesObject)
    }



}