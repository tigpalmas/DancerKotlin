package com.example.tiago.dancerkotlin

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.tiago.dancerkotlin.model.MVP
import com.example.tiago.dancerkotlin.presenter.PresenterLogin
import com.example.tiago.dancerkotlin.utils.Util
import kotlinx.android.synthetic.main.activity_intro.*

import android.provider.SyncStateContract.Helpers.update
import android.content.pm.PackageManager
import android.content.pm.PackageInfo
import android.support.v4.app.FragmentActivity
import android.util.Base64
import android.util.Log
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_cadastrar_dancer.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


open class IntroActivity : AppCompatActivity(), MVP.ViewLogin {


    lateinit var presenter: MVP.PresenterLogin
    lateinit var mProgressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        Picasso.with(this).load(R.drawable.intro_background).into(img_dancer);

        val mPreference = Util.getAppPreference(this)
        if(mPreference.getUser().created){
            goMainActivity()
        }


        presenter = PresenterLogin(this, this)
        presenter.setView(this)
        this.mProgressDialog = ProgressDialog(this)

        txt_entry.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }

        btn_facebook.setOnClickListener{
            presenter.loginFacebook()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(data!=null){
            presenter.onActivityResult(requestCode, resultCode, data);
        }
    }

    override fun showProgressbar(status: Boolean, message: String) {
        Util.showProgressDialog(this, mProgressDialog, status, message)
    }

    override fun showToast(message: String) {
        Util.showToast(this, message)
    }

    override fun goMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        this.finish()
    }
}
