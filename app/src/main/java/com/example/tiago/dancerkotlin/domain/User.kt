package com.example.tiago.dancerkotlin.domain

import java.io.Serializable

/**
 * Created by tiago on 02/01/2018.
 */
class User : Serializable {
    var _id: String? = null
    var dancerId: String? = null
    lateinit var name: String
    var isDancer: Boolean = false
    var acceptTerms: Boolean = false
    var created: Boolean = false
    lateinit var lastName: String
    var city: String? = null;
    lateinit var email: String
    lateinit var password: String
    lateinit var imageURL: String
    lateinit var faceId: String
    lateinit var gender: String


}