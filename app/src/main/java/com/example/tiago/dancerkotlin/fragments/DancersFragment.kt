package com.example.tiago.dancerkotlin.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.tiago.dancerkotlin.R
import com.example.tiago.dancerkotlin.adapters.DancerAdapter
import com.example.tiago.dancerkotlin.domain.Dancer
import com.example.tiago.dancerkotlin.domain.Event
import com.example.tiago.dancerkotlin.model.MVP
import com.example.tiago.dancerkotlin.presenter.PresenterListDancer
import com.example.tiago.dancerkotlin.utils.SpacesItemDecoration
import kotlinx.android.synthetic.main.fragment_dancers.*


/**
 * A simple [Fragment] subclass.
 */
class DancersFragment : Fragment(), MVP.ViewListDancers {


    private var mEvent: Event? = null;
    var presenter: MVP.PresenterListDancers? = null

     var adapter : DancerAdapter? = null;

    companion object {
        val EXTRA_EVENT = "extra_event"
        fun novaInstancia(event: Event): DancersFragment {
            val parametros = Bundle()
            parametros.putSerializable(EXTRA_EVENT, event)
            val fragment = DancersFragment()
            fragment.arguments = parametros
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        retainInstance = true
        mEvent = arguments?.getSerializable(EXTRA_EVENT) as? Event
    }


    public override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                                     savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater!!.inflate(R.layout.fragment_dancers, container, false)
        presenter = PresenterListDancer()
        presenter?.retrieveDancers()
        presenter?.setView(this);

        return view;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        showLoadProgresss(true, "Buscando Programação");
        swipeRefreshLayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {  swipeRefreshLayout?.setRefreshing(false)})

        rv_objects?.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        rv_objects?.addItemDecoration(SpacesItemDecoration(20))
        rv_objects.setHasFixedSize(true);
        adapter = DancerAdapter(presenter?.getDancers()!!, activity);
        rv_objects.adapter = adapter;


    }




    override fun onResume() {
        super.onResume()
        if(mEvent==null){
            (activity as AppCompatActivity).supportActionBar?.title = "Dancers"
        }

    }


    override fun updateList() {
      adapter?.notifyDataSetChanged()
    }

    override fun showLoadProgresss(status: Boolean, message: String) {
        if (status) {
            swipeRefreshLayout?.setRefreshing(true)
        } else {
            swipeRefreshLayout?.setRefreshing(false)
        }
        txt_loading?.setText(message)
    }




}// Required empty public constructor
