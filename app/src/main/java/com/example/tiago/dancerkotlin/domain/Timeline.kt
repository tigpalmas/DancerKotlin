package com.example.tiago.dancerkotlin.domain

import java.io.Serializable

/**
 * Created by tiago on 16/01/2018.
 */
class Timeline: Serializable {
    var city: String? = null
    var date: String? = null
    var skip: Int = 0
}