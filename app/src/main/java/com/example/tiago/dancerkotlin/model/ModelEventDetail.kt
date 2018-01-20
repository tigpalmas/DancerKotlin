package com.example.tiago.dancerkotlin.model

import android.util.Log
import com.example.tiago.dancerkotlin.domain.Contract
import com.example.tiago.dancerkotlin.domain.Dancer
import com.example.tiago.dancerkotlin.domain.User
import com.example.tiago.dancerkotlin.network.DancerProvider
import com.example.tiago.dancerkotlin.network.DancerService
import com.example.tiago.dancerkotlin.utils.ErrorUtils
import com.example.tiago.dancerkotlin.utils.Util
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by tiago on 08/01/2018.
 */
class ModelEventDetail(presenter: MVP.PresenterEventDetail): MVP.ModelEventDetail{

    private val mPresenter: MVP.PresenterEventDetail
    private val service: DancerService

    init {
        val provider = DancerProvider()
        service = provider.getmTService()
        mPresenter = presenter
    }



    override fun registerDancer(contract: Contract) {
        registerContract(contract);
    }


    fun registerContract(contract: Contract){
        val call = service.postContract(contract)
        call.enqueue(object : Callback<Contract>{
            override fun onResponse(call: Call<Contract>?, response: Response<Contract>?) {
                if (response!!.isSuccessful) {
                    mPresenter.showLoadProgresss(false, "Salvo com sucesso")
                } else {
                    val errorModel = ErrorUtils.parseError(response)
                    Log.i("teste", "${errorModel?.message}   ${errorModel?.resMessage}")
                    mPresenter.showLoadProgresss(false, "${errorModel?.message}   ${errorModel?.resMessage}")
                }
            }

            override fun onFailure(call: Call<Contract>?, t: Throwable?) {
                mPresenter.showLoadProgresss(false, t?.message!!)
            }
        })
    }
}