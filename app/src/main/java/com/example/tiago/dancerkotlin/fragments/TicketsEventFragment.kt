package com.example.tiago.dancerkotlin.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.tiago.dancerkotlin.R


/**
 * A simple [Fragment] subclass.
 */
class TicketsEventFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_tickets_event, container, false)
    }

}// Required empty public constructor
