package com.picpay.desafio.android.component

import com.picpay.desafio.android.MainActivity
import com.picpay.desafio.android.module.PicPayModule
import dagger.Component

@Component(modules = [PicPayModule::class])
interface PicPayComponent {
    fun inject(mainActivity: MainActivity)
}