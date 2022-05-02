package com.picpay.desafio.android.datasource

import android.content.Context
import com.picpay.desafio.android.MyApplication
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.service.PicPayService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class UserDataSource(context: Context) {
    @Inject
    lateinit var service : PicPayService
    lateinit var call : Call<List<User>>

    val context: Context = context

    init {
        injectComponents()
    }

    fun getUsers(success: (List<User>) -> Unit, failure: () -> Unit) {
        var users: List<User>
        call = service.getUsers()
        call.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    users = response.body()!!
                    success(users)
                } else {
                    failure()
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable?) {
                failure()
            }
        })
    }

    private fun injectComponents() {
        val myApplication = this.context.applicationContext as MyApplication
        val component = myApplication.picPayComponent
        component.inject(this)
    }
}