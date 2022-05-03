package com.picpay.desafio.android.ui.user

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.model.User
import kotlinx.android.synthetic.main.fragment_list_user.*
import java.nio.channels.Selector

class UserFragment : Fragment() {

    val USER_LIST = "user_list"

    lateinit var viewModel: UserViewModel

    lateinit var progressBar: ProgressBar
    lateinit var recyclerView: RecyclerView

    var users: ArrayList<User>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            users = savedInstanceState?.getParcelableArrayList<User>(USER_LIST)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(
            R.layout.fragment_list_user,
            container,
            false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        configureRecyclerView()

        if (users == null) {
            getUser()
        } else {
            populateRecyclerView(users!!)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(USER_LIST, users)
    }

    private fun getUser() {
        viewModel = UserViewModel(context?.applicationContext!!, this)
        viewModel.loadUsers()
    }

    private fun initViews() {
        progressBar = progress_bar
        progressBar.visibility - View.VISIBLE

        recyclerView = list_users
    }

    private fun configureRecyclerView() {
        val linearLayout = LinearLayoutManager(activity,
            LinearLayoutManager.VERTICAL,
            false)
        val dividerItemDecoration = DividerItemDecoration(activity,
            DividerItemDecoration.VERTICAL)

        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerView.setHasFixedSize(true)
        recyclerView.setItemAnimator(DefaultItemAnimator())
        recyclerView.setLayoutManager(linearLayout)
        recyclerView.setNestedScrollingEnabled(false)
    }

    fun populateRecyclerView(usersList: List<User>) {
        progressBar.visibility - View.GONE

        users = usersList as ArrayList<User>

        val adapter = UserListAdapter()
        adapter.users = users as ArrayList<User>

        recyclerView.adapter = adapter
    }

    fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}