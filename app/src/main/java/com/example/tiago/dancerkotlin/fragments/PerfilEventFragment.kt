package com.example.tiago.dancerkotlin.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.tiago.dancerkotlin.R
import com.example.tiago.dancerkotlin.domain.Event


/**
 * A simple [Fragment] subclass.
 */
class PerfilEventFragment : Fragment() {

    private var mEvent: Event? = null

    companion object {
        val EXTRA_EVENT = "extra_event"
        fun novaInstancia(event: Event): PerfilEventFragment {
            val parametros = Bundle()
            parametros.putSerializable(EXTRA_EVENT, event)
            val fragment = PerfilEventFragment()
            fragment.arguments = parametros
            return fragment
        }
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_perfil_event, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}// Required empty public constructor
