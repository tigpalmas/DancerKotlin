package com.example.tiago.dancerkotlin.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.tiago.dancerkotlin.DancerActivity
import com.example.tiago.dancerkotlin.EventDetailActivity
import com.example.tiago.dancerkotlin.R
import com.example.tiago.dancerkotlin.domain.Dancer
import com.example.tiago.dancerkotlin.domain.Event
import com.squareup.picasso.Picasso

/**
 * Created by tiago on 18/12/2017.
 */

class DancerAdapter(items: List<Dancer>, ctx: Context) : RecyclerView.Adapter<DancerAdapter.ViewHolder>() {
    var list = items;
    var context = ctx;


    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        var item = list.get(position);
        holder?.bind(item, context);
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return DancerAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_dancer, parent, false))
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        lateinit var ctx: Context
        lateinit var dancer: Dancer;
        val imgDancer = itemView?.findViewById<ImageView>(R.id.img_dancer);
        //val txtDate = itemView?.findViewById<TextView>(R.id.txt_date);

        init {
            imgDancer?.setOnClickListener(this)
        }

        fun bind(item: Dancer, ctx: Context) {
            this.ctx = ctx;
            dancer = item;
            //  txtDate?.setText(Util.obterDataPorExtenso(item?.beginDate))
            Picasso.with(ctx).load(item.imageURL).into(imgDancer)

        }

        override fun onClick(p0: View?) {
            val intent = Intent(ctx, DancerActivity::class.java)
            intent.putExtra("extra_dancer", dancer);
            ctx?.startActivity(intent)
        }



    }
}