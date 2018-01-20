package com.example.tiago.dancerkotlin.model

import android.animation.TimeAnimator
import android.app.Activity
import android.content.Context
import android.content.Intent
import com.example.tiago.dancerkotlin.domain.*

/**
 * Created by tiago on 18/12/2017.
 */
interface MVP {

    //LOGIN
    interface  ModelLogin{
        fun loginFacebook()
        fun getActivity(): Activity
        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent)
        fun loginServer( auth: Auth)
        fun showProgressbar(status: Boolean, message: String)
        fun showToast(message: String)


    }

    interface PresenterLogin{
        fun loginFacebook()
        fun getContext(): Context
        fun getActivity(): Activity
        fun setView(view: MVP.ViewLogin)
        fun saveUserPref()
        fun checkUserAuthorizad(): Boolean
        fun goMainActivity();
        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent)
        fun showProgressbar(status: Boolean, message: String)
        fun showToast(message: String)
    }

    interface ViewLogin{
        fun goMainActivity();
        fun showProgressbar(status: Boolean, message: String)
        fun showToast(message: String)

    }


    //List Events
    interface ModelListEvents{
        fun retrieveEvents(timeline: Timeline);
    }

    interface PresenterListEvents{
        fun retrieveEvents(timeline: Timeline);
        fun updateList(events : List<Event>);
        fun clearList();
        fun setView(view: MVP.ViewListEvents);
        fun showLoadProgresss(status: Boolean, message: String);
        fun getEvents(): List<Event>
    }

    interface  ViewListEvents{
        fun updateList();
        fun showLoadProgresss(status: Boolean, message: String)
    }

    //List Model Cadastrar Dancer
    interface ModelRegisterDancer{
        fun retriveStyles();
        fun createDancer(dancer: Dancer)
    }

    interface PresenterRegisterDancer{
        fun retriveStyles();
        fun createDancer(dancer: Dancer)
        fun updateListStyle(styles : List<Style>);
        fun setView(view: MVP.ViewRegisterDancer);
        fun showLoadProgresss( status: Boolean, message: String);
        fun dancerId(dancerId: Dancer)
        fun getStyles(): List<Style>
    }

    interface  ViewRegisterDancer{
        fun updateListStyle();
        fun showLoadProgresss(status: Boolean, message: String)
        fun dancerId(dancerId: Dancer)
    }

    //EventDetailActivity
    interface ModelEventDetail{
        fun registerDancer(contract: Contract);

    }

    interface PresenterEventDetail{
        fun registerDancer(contract: Contract);
        fun showLoadProgresss(status: Boolean, message: String);

    }

    interface  ViewEventDetail{
        fun showLoadProgresss(status: Boolean, message: String)
    }

    //GetDancers
    interface ModelListDancers{
        fun retrieveDancers()
    }

    interface PresenterListDancers{
        fun retrieveDancers()
        fun updateList(dancers : List<Dancer>);
        fun clearList();
        fun setView(view: MVP.ViewListDancers);
        fun showLoadProgresss(status: Boolean, message: String);
        fun getDancers(): List<Dancer>
    }


    interface  ViewListDancers{
        fun updateList();
        fun showLoadProgresss(status: Boolean, message: String)
    }


}