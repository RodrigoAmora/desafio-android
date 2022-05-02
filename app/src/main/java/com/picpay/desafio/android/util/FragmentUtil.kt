package com.picpay.desafio.android.util

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class FragmentUtil {
    companion object {
        fun changeFragment(
            id: Int,
            fragment: Fragment,
            fragmentManager: FragmentManager,
            backstack: Boolean,
            bundle: Bundle?
        ) {
            val transaction = fragmentManager
                .beginTransaction()

            transaction.replace(id, fragment)

            if (backstack) {
                transaction.addToBackStack(null)
            }
            if (bundle != null) {
                fragment.setArguments(bundle)
            }

            transaction.commit()
        }
    }
}