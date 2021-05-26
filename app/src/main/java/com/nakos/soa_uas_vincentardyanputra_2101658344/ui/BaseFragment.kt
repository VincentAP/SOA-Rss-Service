package com.nakos.soa_uas_vincentardyanputra_2101658344.ui

import android.widget.Toast
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}