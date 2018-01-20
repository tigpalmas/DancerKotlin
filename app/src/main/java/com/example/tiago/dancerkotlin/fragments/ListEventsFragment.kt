package com.example.tiago.dancerkotlin.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.*

import com.example.tiago.dancerkotlin.R
import com.example.tiago.dancerkotlin.adapters.EventAdapter
import com.example.tiago.dancerkotlin.dialog_fragments.CitiesDilogFragment
import com.example.tiago.dancerkotlin.domain.Timeline
import com.example.tiago.dancerkotlin.domain.User
import com.example.tiago.dancerkotlin.model.MVP
import com.example.tiago.dancerkotlin.presenter.PresenterListEvent
import com.example.tiago.dancerkotlin.utils.AppPreferenceTools
import com.example.tiago.dancerkotlin.utils.EndlessRecyclerViewScrollListener
import com.example.tiago.dancerkotlin.utils.Util
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import kotlinx.android.synthetic.main.fragment_list_events.*
import java.util.*



/**
 * A simple [Fragment] subclass.
 */
class ListEventsFragment : Fragment(), MVP.ViewListEvents,  DatePickerDialog.OnDateSetListener {
    var presenter: MVP.PresenterListEvents? = null
    var adapter : EventAdapter? = null;
    var timelineFilter= Timeline();
    lateinit var mPreference: AppPreferenceTools;
    lateinit var mUser: User;




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        retainInstance = true
        mPreference = AppPreferenceTools(activity);
        mUser = mPreference.getUser()
        timelineFilter.city = mUser.city
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater!!.inflate(R.layout.fragment_list_events, container, false)
        presenter = PresenterListEvent()
        presenter?.retrieveEvents(timelineFilter)
        presenter?.setView(this);
        return view;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showLoadProgresss(true, "Buscando Programação");
        swipeRefreshLayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {  swipeRefreshLayout?.setRefreshing(false)})
        var activity = activity;
        if(activity !=null){
           val mLinearLayoutManager =   LinearLayoutManager(activity,  LinearLayoutManager.VERTICAL, false)
            rv_events?.layoutManager = mLinearLayoutManager
            rv_events.setHasFixedSize(true);
            adapter = EventAdapter(presenter?.getEvents()!!, activity);
            rv_events.adapter = adapter;


            //Endless Scroll View
            rv_events.addOnScrollListener(object : EndlessRecyclerViewScrollListener(mLinearLayoutManager as LinearLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int) {
                    if(timelineFilter.date==null){
                        swipeRefreshLayout.setRefreshing(true)
                        presenter?.retrieveEvents(timelineFilter)
                        presenter?.showLoadProgresss(false, "")
                    }
                }
            })
        }

        btn_filter_date.setOnClickListener {
            presenter?.clearList()
            timelineFilter.date = null
            timelineFilter.skip = 0
            presenter?.retrieveEvents(timelineFilter);
            rl_filter_date.visibility = View.GONE
        }

    }

    override fun onPrepareOptionsMenu(menu: Menu?) {


        menu?.findItem(R.id.action_calendar)?.isVisible = true

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        when(id){
            R.id.action_calendar ->{
                showDateFilter();
            }


        }
        return super.onOptionsItemSelected(item)
    }


    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        presenter?.clearList()
        val month = monthOfYear+1
        timelineFilter.date =  "$month/$dayOfMonth/$year"
        timelineFilter.skip = 0;

        presenter?.retrieveEvents(timelineFilter)
        rl_filter_date.visibility = View.VISIBLE
        txt_filter_date.text = "Data: $dayOfMonth/$month/$year"
    }

    fun showDateFilter(){
        val now = Calendar.getInstance()
        val dpd = DatePickerDialog.newInstance(
                this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        )
        dpd.show(activity.fragmentManager, "dateCalendarFilter")
    }




    override fun updateList() {
        timelineFilter.skip = presenter?.getEvents()?.count()!!
        adapter?.notifyDataSetChanged()
    }

    override fun showLoadProgresss(status: Boolean, message: String) {
        if (status) {
            swipeRefreshLayout?.setRefreshing(true)
        } else {
            swipeRefreshLayout?.setRefreshing(false)
        }
        txt_loading_events?.setText(message)
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title = "Bailes/Eventos"
    }


}// Required empty public constructor
