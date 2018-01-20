package com.example.tiago.dancerkotlin.domain

import java.io.Serializable

/**
 * Created by tiago on 14/01/2018.
 */
class Address(val _id: String?,
              val city: String?,
              val state: String?,
              val neighborhood: String?,
              val complement: String?,
              val street: String?,
              val number: String?,
              val lat: String,
              val long: String,
              val imageURL: String?,
              var dancers: List<Dancer>?) : Serializable {

}