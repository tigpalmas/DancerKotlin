package com.example.tiago.dancerkotlin

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.example.tiago.dancerkotlin.adapters.ViewPagerCasaDetalheAdapter
import com.example.tiago.dancerkotlin.domain.Dancer
import com.example.tiago.dancerkotlin.fragments.BlankFragment
import com.example.tiago.dancerkotlin.fragments.DancerContractFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_dancer.*
import kotlinx.android.synthetic.main.content_event_detail.*


class DancerActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dancer)


        val item: Dancer? = intent?.getSerializableExtra("extra_dancer") as? Dancer;
        Picasso.with(this).load(item?.imageURL).into(img_user);
        txt_username?.text = item?.name

        var viewPagerCasaDetalheAdapter = ViewPagerCasaDetalheAdapter(supportFragmentManager);
        viewPagerCasaDetalheAdapter.addFragments(BlankFragment(), "Sobre");
        viewPagerCasaDetalheAdapter.addFragments(DancerContractFragment(), "Contratar");



        viewPager?.offscreenPageLimit = 3
        viewPager?.setAdapter(viewPagerCasaDetalheAdapter);
        tabs.setupWithViewPager(viewPager);



    }
}
