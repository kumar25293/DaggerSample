package com.demo.daggersample.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StateList(
    @SerializedName("web_pages" )
    @Expose
    var webPages : ArrayList<String> = arrayListOf(),

    @SerializedName("name")
@Expose
var name : String?  = null,

@SerializedName("state-province")
@Expose
var stateprovince : String?= null,

@SerializedName("domains")
@Expose
var domains : ArrayList<String> = arrayListOf(),

@SerializedName("alpha_two_code")
@Expose
var alphaTwoCode   : String?   = null,

@SerializedName("country")
@Expose
var country : String? = null
)
