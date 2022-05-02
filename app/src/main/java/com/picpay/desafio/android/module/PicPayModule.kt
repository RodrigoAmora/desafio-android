package com.picpay.desafio.android.module

import com.picpay.desafio.android.BuildConfig
import com.picpay.desafio.android.factory.RetrofitFactory
import com.picpay.desafio.android.service.PicPayService
import dagger.Module
import dagger.Provides


@Module
class PicPayModule {
    @Provides
    fun getPicPayService(): PicPayService {
        val retrofit = RetrofitFactory.createRetrofit(BuildConfig.BASE_URL_PICPAY_API)
        return retrofit.create(PicPayService::class.java)
    }
}