package com.picpay.desafio.android.component

import com.picpay.desafio.android.datasource.UserDataSource
import com.picpay.desafio.android.ui.MainActivity
import com.picpay.desafio.android.module.PicPayModule
import com.picpay.desafio.android.ui.user.UserViewModel
import dagger.Component

@Component(modules = [PicPayModule::class])
interface PicPayComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(userVIewModel: UserViewModel)
    fun inject(userDataSource: UserDataSource)
}