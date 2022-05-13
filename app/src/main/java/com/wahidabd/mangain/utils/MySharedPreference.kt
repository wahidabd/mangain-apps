package com.wahidabd.mangain.utils

import android.content.Context
import android.content.SharedPreferences

class MySharedPreference(val context: Context) {

    var pref: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null

    private val PREF = "PREF"
    private val LOGIN = "LOGIN"
    private val NAME = "name"
    private val EMAIL = "email"
    private val PHOTO_URL = "photo"

    init {
        pref = context.getSharedPreferences(PREF, 0)
        editor = pref?.edit()
    }

    var name: String?
        get() = pref?.getString(NAME, "")
        set(value) {
            editor?.putString(NAME, value)
            editor?.commit()
        }

    var email: String?
        get() = pref?.getString(EMAIL, "")
        set(value) {
            editor?.putString(EMAIL, value)
            editor?.commit()
        }

    var photo: String?
        get() = pref?.getString(PHOTO_URL, "")
        set(value) {
            editor?.putString(PHOTO_URL, value)
            editor?.commit()
        }

    var login: Boolean?
        get() = pref?.getBoolean(LOGIN, false)
        set(value){
            value?.let { editor?.putBoolean(LOGIN, it) }
            editor?.commit()
        }

    fun logout(){
        editor?.clear()
        editor?.commit()
    }

}