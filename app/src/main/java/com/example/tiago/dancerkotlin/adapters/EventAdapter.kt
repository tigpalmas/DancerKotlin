package com.example.tiago.dancerkotlin.adapters

import android.content.Context
import android.content.Intent
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.tiago.dancerkotlin.EventDetailActivity
import com.example.tiago.dancerkotlin.R
import com.example.tiago.dancerkotlin.domain.Event
import com.example.tiago.dancerkotlin.fragments.BlankFragment
import com.example.tiago.dancerkotlin.utils.Util
import com.squareup.picasso.Picasso

/**
 * Created by tiago on 18/12/2017.
 */

class EventAdapter(items: List<Event>, ctx: Context) : RecyclerView.Adapter<EventAdapter.ViewHolder>() {
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
        return EventAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_event, parent, false))
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        lateinit var ctx: Context
        lateinit var event: Event;
        val imgEvent = itemView?.findViewById<ImageView>(R.id.iv_event);
        val txtEventName = itemView?.findViewById<TextView>(R.id.txt_event_name);
        val txtEventLocal = itemView?.findViewById<TextView>(R.id.txt_event_local);
        val txtEventCity = itemView?.findViewById<TextView>(R.id.txt_event_city);
        val txtMonth = itemView?.findViewById<TextView>(R.id.txt_month);
        val txtDay = itemView?.findViewById<TextView>(R.id.txt_day);

        init {
            imgEvent?.setOnClickListener(this)
        }

        fun bind(item: Event, ctx: Context) {
            this.ctx = ctx;
            event = item;
            txtEventName?.text = event?.name
            txtEventLocal?.text = event?.addressId?.complement
            txtEventCity?.text = event?.addressId?.city
            txtMonth?.text = Util.obterMonth(event?.date).toUpperCase()
            txtDay?.text = Util.obterDay(event?.date).toUpperCase()
            Picasso.with(ctx).load(item.imageURL).into(imgEvent)

        }

        override fun onClick(p0: View?) {
            val intent = Intent(ctx,EventDetailActivity::class.java)
            intent.putExtra("hello", event);
            ctx.startActivity(intent)
        }



    }
}

