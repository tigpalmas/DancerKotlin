package com.example.tiago.dancerkotlin.presenter

import com.example.tiago.dancerkotlin.domain.Contract
import com.example.tiago.dancerkotlin.model.MVP
import com.example.tiago.dancerkotlin.model.ModelEventDetail

/**
 * Created by tiago on 08/01/2018.
 */
class PresenterEventDetail(view: MVP.ViewEventDetail): MVP.PresenterEventDetail {

    val model: MVP.ModelEventDetail
    val mView: MVP.ViewEventDetail

    init {
        model = ModelEventDetail(this)
        mView = view
    }


    override fun registerDancer(contract: Contract) {
        model.registerDancer(contract)
    }



    override fun showLoadProgresss(status: Boolean, message: String) {
        mView.showLoadProgresss(status, message)
    }
}