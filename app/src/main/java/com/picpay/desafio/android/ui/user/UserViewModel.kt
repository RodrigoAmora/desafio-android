package com.picpay.desafio.android.ui.user

import android.content.Context
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.picpay.desafio.android.R
import com.picpay.desafio.android.datasource.UserDataSource
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.util.CacheUtil
import com.picpay.desafio.android.util.NetworkUtil

class UserViewModel(context: Context, view: UserFragment): ViewModel() {

    val user = ObservableArrayList<User>()

    val context: Context = context
    val view: UserFragment = view

    fun loadUsers() {
        if (NetworkUtil.checkConnection(context)) {
            var userDataSource : UserDataSource = UserDataSource(context)

            userDataSource.getUsers({ items ->
                user.clear()
                user.addAll(items)
                if (items.isEmpty()) {
                    view.showMessage(context.getString(R.string.user_empty))
                }
                view.populateRecyclerView(user.toList())
            }, {
                view.showMessage(context.getString(R.string.error))
            })
        } else {
            val cacheUtil = CacheUtil<List<User>>()
            val userList: List<User>? = cacheUtil.getCache("cache_user")

            view.showMessage(context.getString(R.string.error_no_internet))
            userList?.let { view.populateRecyclerView(it) }
        }
    }

}