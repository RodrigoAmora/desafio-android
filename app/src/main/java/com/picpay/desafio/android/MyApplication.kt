package com.picpay.desafio.android

import android.app.Application
import com.orhanobut.hawk.Hawk
import com.picpay.desafio.android.component.DaggerPicPayComponent
import com.picpay.desafio.android.component.PicPayComponent


class MyApplication : Application() {

    lateinit var picPayComponent: PicPayComponent

    override fun onCreate() {
        super.onCreate()
        initiateHawk()
        createComponents()
    }

    private fun initiateHawk() {
        Hawk.init(this).build()
    }

    private fun createComponents() {
        picPayComponent = DaggerPicPayComponent.create()
    }
}