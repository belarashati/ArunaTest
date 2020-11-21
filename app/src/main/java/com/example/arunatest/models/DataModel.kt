package com.example.arunatest.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DataModel(var titles: String = "", var bodys: String = "") : Serializable {

@SerializedName("userId")
@Expose
var userId : Int? = 0
@SerializedName("id")
@Expose
var Id : Int? = 0
@SerializedName("title")
@Expose
var title: String = ""
@SerializedName("body")
@Expose
var body: String = ""
}
