package com.example.tiago.dancerkotlin.domain

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

/**
 * Created by tiago on 06/01/2018.
 */


class Style: Serializable {
    var name: String? = null
    var _id: String? = null

    private var isChecked: Boolean = false

    fun getChecked(): Boolean {
        return isChecked
    }

    fun setChecked(checked: Boolean) {
        isChecked = checked
    }


}
