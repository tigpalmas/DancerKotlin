package com.example.tiago.dancerkotlin.fragments

import agency.tango.materialintroscreen.SlideFragment
import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tiago.dancerkotlin.R
import com.example.tiago.dancerkotlin.adapters.ProductFilterRecyclerViewAdapter
import com.example.tiago.dancerkotlin.domain.Dancer
import com.example.tiago.dancerkotlin.domain.ListStylesObject
import com.example.tiago.dancerkotlin.domain.Style
import com.example.tiago.dancerkotlin.network.DancerProvider
import com.example.tiago.dancerkotlin.network.DancerService
import com.example.tiago.dancerkotlin.utils.AppPreferenceTools
import kotlinx.android.synthetic.main.dialog_styles.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by tiago on 10/01/2018.
 */
class ListStylesFragment : SlideFragment() {
    private val service: DancerService
    private var finish = false;
    private var mListener: OnCompleteListener? = null
    lateinit var adapter: ProductFilterRecyclerViewAdapter

    init {
        val provider = DancerProvider()
        service = provider.getmTService()
        getStyles()
    }


    private var mDancer: Dancer? = null

    companion object {
        val EXTRA_DANCER = "extra_dancer"
        fun novaInstancia(dancer: Dancer): ListStylesFragment {
            val parametros = Bundle()
            parametros.putSerializable(EXTRA_DANCER, dancer)
            val fragment = ListStylesFragment()
            fragment.arguments = parametros
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDancer = arguments?.getSerializable(EXTRA_DANCER) as Dancer
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_styles, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        rv_styles!!.layoutManager = LinearLayoutManager(activity,  LinearLayoutManager.VERTICAL, false)
        rv_styles.setHasFixedSize(true);


    }



    override fun canMoveFurther(): Boolean {
        if(adapter?.selectedList.size > 0){
            val listObject =  adapter?.selectedList
            mListener?.onComplete(listObject)
            finish =true
        }
        return finish
    }

    override fun cantMoveFurtherErrorMessage(): String {
        return "Você deve selecionar pelo menos um estilo preferido"
    }

    override fun backgroundColor(): Int {
        return R.color.slide_4
    }

    override fun buttonsColor(): Int {
        return R.color.slide_button
    }

    private fun getStyles() {
        val call = service.getStyles()
        call.enqueue(object : Callback<List<Style>> {
            override fun onResponse(call: Call<List<Style>>, response: Response<List<Style>>) {
                if (response.isSuccessful) {
                    val styles = response.body()
                    if (styles.size > 0) {
                        if(activity!=null){


                          adapter  = ProductFilterRecyclerViewAdapter(styles, activity);
                            rv_styles.adapter = adapter;
                        }

                    } else {

                    }
                } else {

                }
            }

            override fun onFailure(call: Call<List<Style>>, t: Throwable) {

            }
        })
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
        fun onComplete(list: List<Style>)
    }


}