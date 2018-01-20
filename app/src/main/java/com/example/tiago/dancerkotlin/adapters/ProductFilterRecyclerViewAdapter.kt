package com.example.tiago.dancerkotlin.adapters

import android.content.Context
import android.widget.TextView
import android.widget.Toast
import android.widget.CompoundButton
import android.widget.CheckBox
import android.support.v7.widget.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tiago.dancerkotlin.R
import com.example.tiago.dancerkotlin.domain.Style





/**
 * Created by tiago on 06/01/2018.
 */

class ProductFilterRecyclerViewAdapter(private val filterList: List<Style>, private val context: Context) : RecyclerView.Adapter<ProductFilterRecyclerViewAdapter.ViewHolder>() {
    val selectedList = ArrayList<Style>()

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ProductFilterRecyclerViewAdapter.ViewHolder {

        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_style, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position);

    }

    override fun getItemCount(): Int {
        return filterList.size
    }

    fun getSelectedList(): List<Style>{
        return selectedList;
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        var selectionState: CheckBox
        val txtNameStyle = view?.findViewById<TextView>(R.id.brand_name);

        init {

            selectionState = view.findViewById(R.id.brand_select)
            this.setIsRecyclable(false);

            //item click event listener
            view.setOnClickListener(this)

            //checkbox click event handling
            selectionState.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {

                } else {

                }
            }
        }

        override fun onClick(v: View) {
            val adapterPosition = adapterPosition
            if (filterList.get(adapterPosition).getChecked()) {
                selectionState.setChecked(false)
                filterList.get(adapterPosition).setChecked(false)
                if(selectedList.contains(filterList.get(adapterPosition))){
                    selectedList.remove(filterList.get(adapterPosition))
                }

            } else {
                selectionState.setChecked(true)
                filterList.get(adapterPosition).setChecked(true)
                if(!selectedList.contains(filterList.get(adapterPosition))){
                    selectedList.add(filterList.get(adapterPosition))
                }
            }
        }

        fun bind(position: Int) {
            val style = filterList.get(position);
            txtNameStyle.text = style.name

        }
    }
}
