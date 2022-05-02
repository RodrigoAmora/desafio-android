package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.component.DaggerPicPayComponent
import com.picpay.desafio.android.component.PicPayComponent

class MyApplication : Application() {

    lateinit var picPayComponent: PicPayComponent

    override fun onCreate() {
        super.onCreate()
        createComponents()
    }
    private fun createComponents() {
        picPayComponent = DaggerPicPayComponent.create()
    }
}