package com.example.tiago.dancerkotlin.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tiago.dancerkotlin.CadastrarDancerActivity
import com.example.tiago.dancerkotlin.TermsDancerActivity

import com.example.tiago.dancerkotlin.R
import com.example.tiago.dancerkotlin.dialog_fragments.CitiesDilogFragment
import com.example.tiago.dancerkotlin.domain.User
import com.example.tiago.dancerkotlin.utils.AppPreferenceTools
import com.example.tiago.dancerkotlin.utils.Util
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_perfil.*


/**
 * A simple [Fragment] subclass.
 */
class UserPerfilFragment : Fragment() {

    lateinit var mPreference: AppPreferenceTools
    lateinit var mUser: User


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_perfil, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title = "Seu Perfil"
        mPreference = Util.getAppPreference(activity)
        mUser = mPreference.getUser()
        setViews()
    }

    fun setViews() {
        if (!mUser.created!!) {
            txt_username?.text = "Não Logado"
            txt_useremail?.text = ""
           txt_dancer.text = ""
          // btn_facebook.visibility = View.VISIBLE
        } else {
            Picasso.with(activity).load(mUser?.imageURL).into(img_user)
            txt_username?.text = "${mUser?.name}"
            txt_useremail?.text = mUser?.email
            txt_city?.text = mUser?.city
            if (mUser?.acceptTerms!!) {
                txt_dancer.text = "Edite seu perfil Dancer"
            }
        }

        rl_dancer.setOnClickListener {
            if (mUser.acceptTerms!!) {
                val intent = Intent(activity, CadastrarDancerActivity::class.java)
                intent.putExtra("user_extra", mUser);
                activity.startActivity(intent)
            } else {
                val intent = Intent(activity, TermsDancerActivity::class.java)
                activity.startActivity(intent)
            }

        }

        rl_city.setOnClickListener {
            val fragment = CitiesDilogFragment()
            fragment.setTargetFragment(this, 1)
            val fragmentTransaction = activity.supportFragmentManager.beginTransaction()
            fragment.show(fragmentTransaction, "cities")

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //do what ever you want here, and get the result from intent like below
        if (resultCode == CitiesDilogFragment.RESULT_CODE) {
            txt_city?.text = data?.getStringExtra("listdata")
        }
    }




}// Required empty public constructor
