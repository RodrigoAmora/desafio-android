package com.picpay.desafio.android.ui.user

import android.os.Bundle
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

    lateinit var viewModel: UserViewModel

    lateinit var progressBar: ProgressBar
    lateinit var recyclerView: RecyclerView

    lateinit var users: List<User>

    override fun onStart() {
        super.onStart()
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
        getUser()
        initViews()
        configureRecyclerView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    private fun getUser() {
        //viewModel.loadUsers()
        viewModel = UserViewModel(context?.applicationContext!!, this)
    }

    private fun initViews() {
        progressBar = progress_bar
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

    fun populateRecyclerView(users: List<User>) {
        val adapter = UserListAdapter()
        adapter.users = users
        recyclerView.adapter = adapter
    }

    fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}