package com.nakos.soa_uas_vincentardyanputra_2101658344.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.nakos.soa_uas_vincentardyanputra_2101658344.Status
import com.nakos.soa_uas_vincentardyanputra_2101658344.adapter.NyTimesAdapter
import com.nakos.soa_uas_vincentardyanputra_2101658344.databinding.FragmentNytimesBinding
import com.nakos.soa_uas_vincentardyanputra_2101658344.viewmodel.NyTimesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NyTimesFragment : BaseFragment() {

    private var binding: FragmentNytimesBinding? = null

    private val nyTimesViewModel: NyTimesViewModel by viewModels()

    private val recyclerAdapter: NyTimesAdapter by lazy { NyTimesAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNytimesBinding.inflate(inflater, container, false)

        binding?.apply {
            recyclerNyTimes.adapter = recyclerAdapter.setNyTimesListener { str ->
                Intent(Intent.ACTION_VIEW, Uri.parse(str)).also {
                    startActivity(it)
                }
            }
        }

        setupNyTimesViewModel()
        return binding?.root
    }

    private fun setupNyTimesViewModel() {
        nyTimesViewModel.getNyTimesItem()
        nyTimesViewModel.nyTimesItem.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> binding?.progressBar?.visibility = View.VISIBLE
                Status.SUCCESS -> {
                    binding?.progressBar?.visibility = View.GONE
                    recyclerAdapter.submitList(it.data)
                }
                else -> {
                    binding?.progressBar?.visibility = View.GONE
                    showToast(it.message ?: "unknown error")
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}