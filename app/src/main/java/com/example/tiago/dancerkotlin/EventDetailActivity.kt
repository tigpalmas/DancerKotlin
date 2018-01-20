package com.example.tiago.dancerkotlin

import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import com.example.tiago.dancerkotlin.adapters.ViewPagerCasaDetalheAdapter
import com.example.tiago.dancerkotlin.domain.Event
import com.example.tiago.dancerkotlin.utils.Util
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_event_detail.*
import kotlinx.android.synthetic.main.content_event_detail.*
import android.widget.RelativeLayout
import android.widget.NumberPicker
import com.example.tiago.dancerkotlin.domain.Contract
import com.example.tiago.dancerkotlin.fragments.*
import com.example.tiago.dancerkotlin.model.MVP
import com.example.tiago.dancerkotlin.presenter.PresenterEventDetail


class EventDetailActivity : AppCompatActivity(), MVP.ViewEventDetail {
    var presenter: MVP.PresenterEventDetail
    lateinit var mEvent: Event
    lateinit var progress: ProgressDialog



     init {
         presenter = PresenterEventDetail(this)
     }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);



        progress = ProgressDialog(this)
        mEvent  = intent?.getSerializableExtra("hello") as Event;

        supportActionBar?.setTitle(mEvent.name);
        Picasso.with(this).load(mEvent.imageURL).into(img_event_detail);

        val dancerFragment = DancersFragment.novaInstancia(mEvent)
        val mapFragment = MapFragment.novaInstancia(mEvent)
        val eventDetailFragment = PerfilEventFragment.novaInstancia(mEvent)
        var viewPagerCasaDetalheAdapter = ViewPagerCasaDetalheAdapter(supportFragmentManager);

        viewPagerCasaDetalheAdapter.addFragments(eventDetailFragment, "Detalhes");
        viewPagerCasaDetalheAdapter.addFragments(mapFragment, "Localização");
        viewPagerCasaDetalheAdapter.addFragments(TicketsEventFragment(), "Ingressos");
        viewPagerCasaDetalheAdapter.addFragments(dancerFragment, "Dancers");



        viewPager?.offscreenPageLimit = 3
        viewPager?.setAdapter(viewPagerCasaDetalheAdapter);
        tabs.setupWithViewPager(viewPager);


    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_event_detail, menu)
        val menu = menu!!.findItem(R.id.action_add_dancer)
        menu.isVisible = true

        return true
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.action_add_dancer ->{
                val linearLayout = RelativeLayout(this)
                val aNumberPicker = NumberPicker(this)
                aNumberPicker.maxValue = 10
                aNumberPicker.minValue = 1

                val params = RelativeLayout.LayoutParams(50, 50)
                val numPicerParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
                numPicerParams.addRule(RelativeLayout.CENTER_HORIZONTAL)

                linearLayout.layoutParams = params
                linearLayout.addView(aNumberPicker, numPicerParams)


                val builder = AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle)
                builder.setTitle("Se inscrever como personal Dancer ?")
                builder.setView(linearLayout)
                builder.setMessage("Selecione o número de horas")
                builder.setPositiveButton("Confirmar" , DialogInterface.OnClickListener { dialog, id ->
                    Util.showProgressDialog(this, progress, true, "Cadastrando...")
                    var contract = Contract()
                    contract.dancerFk = "5a540562090c7f0014f1d746"
                    contract.userFk = "5a540551090c7f0014f1d745"
                    contract.eventFk = mEvent._id
                    contract.numberOfHours = aNumberPicker.value


                    presenter.registerDancer(contract)
                })
                builder.setNegativeButton("Cancelar", null)
                builder.show()

            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun showLoadProgresss(status: Boolean, message: String) {
        Util.showProgressDialog(this, progress, false, "Cadastrando...")
        Util.showToast(this, "Salvo com sucesso")
    }


}
