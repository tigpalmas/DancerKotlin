package com.example.tiago.dancerkotlin.dialog_fragments

import android.app.Activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.tiago.dancerkotlin.R
import com.example.tiago.dancerkotlin.adapters.CitiesAdapter
import com.example.tiago.dancerkotlin.adapters.ProductFilterRecyclerViewAdapter
import com.example.tiago.dancerkotlin.domain.Dancer
import com.example.tiago.dancerkotlin.domain.Event
import com.example.tiago.dancerkotlin.domain.ListStylesObject
import com.example.tiago.dancerkotlin.domain.User
import com.example.tiago.dancerkotlin.fragments.MapFragment
import com.example.tiago.dancerkotlin.utils.AppPreferenceTools
import com.webianks.library.scroll_choice.ScrollChoice
import kotlinx.android.synthetic.main.dialog_cities.*
import java.util.ArrayList

/**
 * Created by tiago on 05/01/2018.
 */
class CitiesDilogFragment : DialogFragment() {



    private var mCities = arrayListOf<String>()
    var mPreference: AppPreferenceTools? =null;
    var mUser: User? = null;


    companion object {
        public  val   RESULT_CODE = 25
        val EXTRA_EVENT = "extra_event"
        fun novaInstancia(text: String): CitiesDilogFragment {
            val parametros = Bundle()
            parametros.putSerializable(EXTRA_EVENT, text)
            val fragment = CitiesDilogFragment()
            fragment.arguments = parametros
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPreference = AppPreferenceTools(activity);
        mUser = mPreference?.getUser()

        mCities.add("Curitiba - PR")
        mCities.add("Rio de Janeiro - RJ")
        mCities.add("Florianópolis - SC")
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.dialog_cities, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        scroll_choice.addItems(mCities, 0)
        mUser?.city = "Curitiba"
        scroll_choice.setOnItemSelectedListener(ScrollChoice.OnItemSelectedListener { scrollChoice, position, name ->
            if (position == 0) {
                mUser?.city = "Curitiba"
            } else if(position ==1){
                mUser?.city = "Rio de Janeiro"
            }else if(position ==2){
                mUser?.city = "Florianopolis"
            }
        })

        btn_save.setOnClickListener {
            if(mUser?.city!=null){

                mPreference?.saveUserAuthenticationInfo(mUser!!);
            }

            val intent = Intent()
            intent.putExtra("listdata", mUser?.city )
            targetFragment.onActivityResult(targetRequestCode, RESULT_CODE, intent)
            this.dismiss()
        }
    }

    override fun onStart() {
        super.onStart()


        if (dialog != null) {
            dialog.window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            //dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }




}