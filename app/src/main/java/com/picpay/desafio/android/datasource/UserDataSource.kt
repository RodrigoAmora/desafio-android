package com.picpay.desafio.android.datasource

import android.content.Context
import com.picpay.desafio.android.MyApplication
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.service.PicPayService
import com.picpay.desafio.android.util.CacheUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class UserDataSource(context: Context) {
    val CACHE_USER = "cache_user"

    @Inject
    lateinit var service : PicPayService
    lateinit var call : Call<List<User>>
    lateinit var cacheUtil: CacheUtil<List<User>>

    val context: Context = context

    init {
        cacheUtil = CacheUtil()
        injectComponents()
    }

    fun getUsers(success: (List<User>) -> Unit, failure: () -> Unit) {
        var users: List<User>
        call = service.getUsers()
        call.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    users = response.body()!!
                    if (!cacheUtil.getCache(CACHE_USER).isNullOrEmpty()) {
                        cacheUtil.removeCache(CACHE_USER)
                    }
                    cacheUtil.saveCache(CACHE_USER, users)
                    success(users)
                } else {
                    failure()
                    users = cacheUtil.getCache(CACHE_USER)!!
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable?) {
                failure()
                users = cacheUtil.getCache(CACHE_USER)!!
            }
        })
    }

    private fun injectComponents() {
        val myApplication = this.context.applicationContext as MyApplication
        val component = myApplication.picPayComponent
        component.inject(this)
    }
}