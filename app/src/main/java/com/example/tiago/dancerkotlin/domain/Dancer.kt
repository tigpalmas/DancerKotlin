package com.example.tiago.dancerkotlin.domain

import java.io.Serializable

/**
 * Created by tiago on 02/01/2018.
 */
class Dancer(): Serializable {
    var _id: String? = null
    var userId: String? = null
    var description: String? = null
    var imageURL: String? = null
    var name: String? = null
    var priceHour: String? = null
    var school: String? = null
    var level: String? = null
    var phone: String? = null
    var styles: List<String>? = null
    var imagePath: String? = null
}