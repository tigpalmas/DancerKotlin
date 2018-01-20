package com.example.tiago.dancerkotlin.domain

import java.io.Serializable
import java.util.*
/**
 * Created by tiago on 18/12/2017.
 */
class Event(val _id: String?,
            val name: String?,
            val date: Date?,
            val addressId: Address?,
             val imageURL: String?,
            var dancers: List<Dancer>?):Serializable {




}