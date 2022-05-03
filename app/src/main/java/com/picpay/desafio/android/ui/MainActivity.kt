package com.picpay.desafio.android.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.R
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.ui.user.UserListAdapter
import com.picpay.desafio.android.service.PicPayService
import com.picpay.desafio.android.ui.user.UserFragment
import com.picpay.desafio.android.util.FragmentUtil
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    val USER_FRAGMENT = "USER_FRAGMENT"

    lateinit var userFragment: UserFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            userFragment = getSupportFragmentManager().
                            getFragment(savedInstanceState, USER_FRAGMENT) as UserFragment
        } else {
            userFragment = UserFragment()
            changeFragment(userFragment, null, false)
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        getSupportFragmentManager().putFragment(outState,
                                                USER_FRAGMENT,
                                                userFragment)
    }

    private fun changeFragment(fragment: Fragment, bundle: Bundle?, backstack: Boolean) {
        FragmentUtil.changeFragment(R.id.container, fragment, getSupportFragmentManager(), backstack, bundle)
    }
}
