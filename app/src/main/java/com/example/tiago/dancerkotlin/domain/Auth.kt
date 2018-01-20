package com.example.tiago.dancerkotlin.domain

import java.io.Serializable

/**
 * Created by tiago on 16/01/2018.
 */
class Auth : Serializable {
    var id: String? = null
    var email: String? = null
    var faceId: String? = null
    var userData: User? = null
    var name: String? = null
    var lastName: String? = null
    var gender: String? = null
    var imageURL: String? = null
    var password: String? = null
}