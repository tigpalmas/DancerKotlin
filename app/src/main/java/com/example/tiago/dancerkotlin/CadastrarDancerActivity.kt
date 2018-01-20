package com.example.tiago.dancerkotlin


import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.example.tiago.dancerkotlin.dialog_fragments.EditTextDialog
import com.example.tiago.dancerkotlin.domain.Dancer
import com.example.tiago.dancerkotlin.domain.Style
import com.example.tiago.dancerkotlin.domain.User
import com.example.tiago.dancerkotlin.model.MVP
import com.example.tiago.dancerkotlin.presenter.PresenterCadastrarDancer

import com.squareup.picasso.Picasso


import android.R.array
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.util.Log


import com.example.tiago.dancerkotlin.dialog_fragments.StylesDialogFragment
import com.example.tiago.dancerkotlin.domain.ListStylesObject
import com.example.tiago.dancerkotlin.utils.AppPreferenceTools
import com.example.tiago.dancerkotlin.utils.Util
import com.nguyenhoanglam.imagepicker.model.Config
import com.nguyenhoanglam.imagepicker.model.Image
import com.nguyenhoanglam.imagepicker.ui.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_cadastrar_dancer.*
import kotlinx.android.synthetic.main.content_cadastrar_dancer.*
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.widget.EditText
import com.example.tiago.dancerkotlin.dialog_fragments.CitiesDilogFragment
import com.example.tiago.dancerkotlin.dialog_fragments.LevelDialog


class CadastrarDancerActivity : AppCompatActivity(),
        StylesDialogFragment.OnCompleteListener,
        LevelDialog.OnCompleteListener,
        MVP.ViewRegisterDancer {


    var mDancer: Dancer? = null
    var mUser: User? = null
    var presenter: MVP.PresenterRegisterDancer? = null
    var mStyles = arrayListOf<Style>();
    var mPreference: AppPreferenceTools? = null
    var progressDialog: ProgressDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar_dancer)
        setSupportActionBar(toolbar)
        progressDialog = ProgressDialog(this)

        mUser = intent?.getSerializableExtra("user_extra") as? User;
        Picasso.with(this).load(mUser?.imageURL).into(img_user);
        txt_username?.text = mUser?.name

        mPreference = Util.getAppPreference(this)
        mDancer = mPreference?.getDancer()
        setViews();

        presenter = PresenterCadastrarDancer()
        presenter?.setView(this)
        presenter?.retriveStyles()



        txt_level.setOnClickListener{
            val fragment = LevelDialog()
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragment.show(fragmentTransaction, "cities")
        }


        btn_save.setOnClickListener {
            if (checkErrors()) {
                buildDancer()
                Util.showProgressDialog(this, progressDialog, true, "Salvando seu Perfil Dancer")
                mPreference?.saveDancer(mDancer!!)
                presenter?.createDancer(mDancer!!)
            }
        }

        ll_styles.setOnClickListener {
            showTagDialog()
        }


        edit_image_background.setOnClickListener {
            ImagePicker.with(this)                         //  Initialize ImagePicker with activity or fragment context
                    .setToolbarColor("#212121")         //  Toolbar color
                    .setStatusBarColor("#000000")       //  StatusBar color (works with SDK >= 21  )
                    .setToolbarTextColor("#FFFFFF")     //  Toolbar text color (Title and Done button)
                    .setToolbarIconColor("#FFFFFF")     //  Toolbar icon color (Back and Camera button)
                    .setProgressBarColor("#4CAF50")     //  ProgressBar color
                    .setBackgroundColor("#212121")      //  Background color
                    .setFolderMode(true)                //  Folder mode
                    .setMaxSize(1)                     //  Max images can be selected
                    .setSavePath("Personal Dancer")         //  Image capture folder name
                    .setKeepScreenOn(true)              //  Keep screen on when selecting images
                    .start();
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == Config.RC_PICK_IMAGES && resultCode == Activity.RESULT_OK && data != null) {
            val images = data.getParcelableArrayListExtra<Image>(Config.EXTRA_IMAGES)
            val image = images.get(0)

            mDancer?.imagePath = image.path
            val bmImg = BitmapFactory.decodeFile(image.path)
            img_background.setImageBitmap(bmImg)
        }

        super.onActivityResult(requestCode, resultCode, data)


    }

    private fun isEmpty(etText: EditText): Boolean {
        return etText.text.toString().trim { it <= ' ' }.length == 0
    }

    private fun checkErrors(): Boolean {
        var error = true
        if (isEmpty(edt_about)) {
            edt_about?.error = "Campo não pode estar vazio"
            error = false
        }
       /* if (isEmpty(edt_level)) {
            edt_level?.error = "Campo não pode estar vazio"
            error = false
        }*/
        if (isEmpty(edt_phone)) {
            edt_phone.error = "Campo não pode estar vazio"
            error = false
        }

        if (isEmpty(edt_school)) {
            edt_school?.error = "Campo não pode estar vazio"
            error = false
        }
        if (isEmpty(edt_value_hour)) {
            edt_value_hour?.error = "Campo não pode estar vazio"
            error = false
        }

        if (mDancer?.styles == null) {
            error = false
            Util.showToast(this, "Voce deve adicionar pelo menos 1 estilo");
        }
        if (mDancer?.imagePath == null) {
            error = false
            Util.showToast(this, "Selecione uma Imagem de Background");
        }


        return error;
    }

    private fun buildDancer() {
        mDancer?.userId = mUser?._id
        mDancer?.description = edt_about.text.toString().trim()
     //   mDancer?.level = edt_level.text.toString().trim()
        mDancer?.priceHour = edt_value_hour.text.toString().trim()
        mDancer?.phone = edt_phone.text.toString().trim()
        mDancer?.school = edt_school.text.toString().trim()
    }


    override fun onComplete(objectList: ListStylesObject) {
        val tags = StringBuilder()
        val list = ArrayList<String>()
        for (style in objectList.styles!!) {
            tags.append("${style.name} - ")
            list.add(style._id.toString()!!)
        }

        mDancer?.styles = list
        txt_tags.text = tags
    }

    override fun onComplete(level: String) {
        mDancer?.level = level;
       txt_level.text = level
    }



    override fun updateListStyle() {

    }

    override fun showLoadProgresss(status: Boolean, message: String) {
        if (!status) {
            Util.showProgressDialog(this, progressDialog, false, message)
        }
    }


    fun showTagDialog() {
        val list = ListStylesObject()
        list.styles = presenter?.getStyles()
        val pop = StylesDialogFragment.novaInstancia(list)
        val fm = this@CadastrarDancerActivity.fragmentManager
        pop.show(fm, "styles")
    }

    override fun dancerId(dancerId: Dancer) {

        Util.showToast(this, "Dancer Salvo com sucesso")
    }


    private fun setViews(){
         edt_about.setText(mDancer?.description)
         //edt_level.setText(mDancer?.level)
         edt_value_hour.setText(mDancer?.priceHour)
         edt_school.setText(mDancer?.school)
         edt_phone.setText(mDancer?.phone)
        if(mDancer?.imagePath!=null){
            val bmImg = BitmapFactory.decodeFile(mDancer?.imagePath)
            img_background.setImageBitmap(bmImg)
        }



    }


}
